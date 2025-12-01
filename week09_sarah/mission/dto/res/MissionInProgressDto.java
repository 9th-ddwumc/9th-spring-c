package com.example.umc9th2.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MissionInProgressDto {
    private Long missionChallengeId;
    private Long missionId;
    private String conditional;
    private LocalDate deadline;
    private Integer point;
    private String storeName;
}
