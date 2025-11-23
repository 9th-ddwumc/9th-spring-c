package com.example.umc9th2.domain.mission.service;

import com.example.umc9th2.domain.mission.dto.res.MissionCompleteResponseDto;
import com.example.umc9th2.domain.mission.dto.res.MissionInProgressDto;
import com.example.umc9th2.domain.mission.entity.MissionChallenge;
import com.example.umc9th2.domain.mission.repository.MissionChallengeRepository;
import com.example.umc9th2.domain.mission.repository.MissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionChallengeRepository missionChallengeRepository;

    public Page<MissionInProgressDto> getMyInProgressMissions(Long memberId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<MissionChallenge> mcPage =
                missionChallengeRepository.findByMemberIdAndStatus(memberId, "in-progress", pageable);

        return mcPage.map(mc ->
                MissionInProgressDto.builder()
                        .missionChallengeId(mc.getId())
                        .missionId(mc.getMission().getId())
                        .conditional(mc.getMission().getConditional())
                        .deadline(mc.getMission().getDeadline())
                        .point(mc.getMission().getPoint())
                        .storeName(mc.getMission().getStore().getName())
                        .build()
        );
    }

    @Transactional
    public MissionCompleteResponseDto completeMission(Long missionChallengeId) {

        MissionChallenge mc = missionChallengeRepository.findById(missionChallengeId)
                .orElseThrow(() -> new IllegalArgumentException("미션 도전 정보를 찾을 수 없습니다."));

        mc.setStatus("completed");
        mc.setCompletedAt(LocalDate.now());

        return MissionCompleteResponseDto.builder()
                .missionChallengeId(mc.getId())
                .status(mc.getStatus())
                .completedAt(mc.getCompletedAt())
                .build();
    }
}
