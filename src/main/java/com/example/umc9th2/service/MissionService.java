package com.example.umc9th2.service;

import com.example.umc9th2.converter.MissionConverter;
import com.example.umc9th2.domain.mission.Mission;
import com.example.umc9th2.domain.mission.MissionStatus;
import com.example.umc9th2.domain.mission.UserMissionStatus;
import com.example.umc9th2.domain.store.Store;
import com.example.umc9th2.domain.user.User;
import com.example.umc9th2.dto.HomeMissionCardDto;
import com.example.umc9th2.dto.MissionResDTO;
import com.example.umc9th2.repository.MissionRepository;
import com.example.umc9th2.repository.StoreRepository;
import com.example.umc9th2.repository.UserMissionStatusRepository;
import com.example.umc9th2.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionStatusRepository userMissionStatusRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    // 로그인 기능이 없으므로, 미션 도전하는 유저는 하드코딩
    private static final Long FIXED_USER_ID = 1L;

    // ✅ 기존: 특정 지역의 오픈 미션 카드 조회
    public Page<HomeMissionCardDto> getOpenMissionsByRegion(Long regionId, Pageable pageable) {
        return missionRepository.findOpenByRegion(regionId, pageable);
    }
    // page는 1부터 들어오고, 내부에선 0-based 로 변환
    public MissionResDTO.StoreMissionListDTO getStoreMissions(Long storeId, int page) {
        PageRequest pageable = PageRequest.of(page - 1, 10); // 한 페이지 10개 고정

        Page<Mission> result =
                missionRepository.findByStoreIdOrderBySortOrderDescIdDesc(storeId, pageable);

        return MissionConverter.toStoreMissionListDTO(result);
    }

    //  추가: 가게의 미션을 "도전 중" 상태에 추가 (미션 도전하기)
    @Transactional
    public Long challengeMission(Long storeId, Long missionId) {

        // 1) 가게 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다."));

        // 2) 미션 조회
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("미션을 찾을 수 없습니다."));

        // 3) 이 미션이 해당 가게의 미션인지 검증
        if (mission.getStore() == null || !mission.getStore().getId().equals(store.getId())) {
            throw new IllegalArgumentException("해당 가게의 미션이 아닙니다.");
        }

        // 4) 유저 하드코딩 조회
        User user = userRepository.findById(FIXED_USER_ID)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        // 5) 이미 도전 중인지 중복 체크
        if (userMissionStatusRepository.existsByUserAndMission(user, mission)) {
            throw new IllegalStateException("이미 도전 중인 미션입니다.");
        }

        // 6) UserMissionStatus 생성 (도전 중 상태)
        UserMissionStatus status = UserMissionStatus.builder()
                .user(user)
                .mission(mission)
                .status(MissionStatus.IN_PROGRESS) // enum 이름은 프로젝트에 맞게 사용
                .build();

        // 7) 저장 후 id 반환
        return userMissionStatusRepository.save(status).getId();
    }
}
