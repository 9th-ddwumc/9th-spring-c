package com.example.umc9th2.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MissionChallengeResponseDto {
    private Long missionChallengeId;
    private Long missionId;
    private Long memberId;
    private String status;
    private String message;
}
