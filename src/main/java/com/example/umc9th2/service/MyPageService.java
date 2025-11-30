package com.example.umc9th2.service;

import com.example.umc9th2.converter.MissionConverter;
import com.example.umc9th2.domain.award.RegionCompletionAward;
import com.example.umc9th2.domain.mission.MissionStatus;
import com.example.umc9th2.dto.MissionResDTO;
import com.example.umc9th2.dto.MyMissionCardDto;
import com.example.umc9th2.repository.RegionCompletionAwardRepository;
import com.example.umc9th2.repository.UserMissionCompletionRepository;
import com.example.umc9th2.repository.UserMissionStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserMissionCompletionRepository umcRepo;
    private final RegionCompletionAwardRepository awardRepo;
    private final UserMissionStatusRepository umsRepo;

    public Page<MyMissionCardDto> getMyMissions(Long userId, Pageable pageable) {
        return umcRepo.findMyMissionCards(userId, pageable);
    }

    public List<RegionCompletionAward> getMyAwards(Long userId) {
        return awardRepo.findAwardsByUser(userId);
    }

    public MissionResDTO.MyInProgressMissionListDTO getMyInProgressMissions(Long userId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);  // 1-based → 0-based 변환

        var result = umsRepo.findByUserIdAndStatus(
                userId,
                MissionStatus.IN_PROGRESS,
                pageable
        );

        return MissionConverter.toMyInProgressMissionListDTO(result);
    }
}
