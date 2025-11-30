package com.example.umc9th.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

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

    @Builder
    public record OngoingMissionListDTO(
            List<OngoingMissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record OngoingMissionDTO(
            Long memberMissionId,
            Long missionId,
            String storeName,
            Integer cost,
            Integer point,
            Boolean isComplete
    ) {}
}