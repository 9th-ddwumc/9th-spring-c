package com.example.umc9th2.dto;

import com.example.umc9th2.domain.mission.MissionStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    // 📌 특정 가게의 미션 한 개 카드
    @Builder
    public record StoreMissionDTO(
            Long missionId,
            String missionTitle,
            String missionDescription,
            String storeName
    ) {}

    // 📌 특정 가게의 미션 목록 + 페이지 정보
    @Builder
    public record StoreMissionListDTO(
            List<StoreMissionDTO> missionList,
            int listSize,
            int totalPage,
            long totalElements,
            boolean isFirst,
            boolean isLast
    ) {}

    // ----------------------------
    // 3번 API: 내가 진행중인 미션 목록
    // ----------------------------
    @Builder
    public record MyInProgressMissionDTO(
            Long userMissionStatusId,
            Long missionId,
            String storeName,
            String missionTitle,
            MissionStatus status,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record MyInProgressMissionListDTO(
            List<MyInProgressMissionDTO> missionList,
            int listSize,
            int totalPage,
            long totalElements,
            boolean isFirst,
            boolean isLast
    ) {}
}
