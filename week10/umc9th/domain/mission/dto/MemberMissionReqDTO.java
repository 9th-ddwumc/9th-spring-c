package com.example.umc9th.domain.mission.dto;

import jakarta.validation.constraints.NotNull;

public class MemberMissionReqDTO {

    public record ChallengeDTO(
            Long missionId
    ){}
}