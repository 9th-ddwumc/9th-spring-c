package com.example.umc9th.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

public class MemberMissionResDTO {

    @Builder
    public record ChallengeDTO(
            Long memberMissionId,
            Long missionId,
            Long storeId,
            String storeName,
            Integer point,
            boolean isComplete
    ){}
}
