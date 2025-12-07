package com.example.umc9th2.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MissionCompleteResponseDto {
    private Long missionChallengeId;
    private String status;
    private LocalDate completedAt;
}
