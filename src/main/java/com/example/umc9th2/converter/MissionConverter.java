package com.example.umc9th2.converter;

import com.example.umc9th2.domain.mission.Mission;
import com.example.umc9th2.domain.mission.UserMissionStatus;
import com.example.umc9th2.dto.MissionResDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    // Page<Mission> → StoreMissionListDTO
    public static MissionResDTO.StoreMissionListDTO toStoreMissionListDTO(
            Page<Mission> result
    ) {
        List<MissionResDTO.StoreMissionDTO> content = result.getContent().stream()
                .map(MissionConverter::toStoreMissionDTO)
                .toList(); // ⭐ for문 사용 금지 조건 → stream 사용

        return MissionResDTO.StoreMissionListDTO.builder()
                .missionList(content)
                .listSize(result.getSize())           // 한 페이지 크기(=10)
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    // Mission → StoreMissionDTO
    public static MissionResDTO.StoreMissionDTO toStoreMissionDTO(Mission mission) {
        return MissionResDTO.StoreMissionDTO.builder()
                .missionId(mission.getId())
                .missionTitle(mission.getTitle())
                .missionDescription(mission.getDescription())
                .storeName(mission.getStore().getName())
                .build();
    }

    // ----------------------------
    // 3번 API: 내가 진행중인 미션 목록 변환
    // ----------------------------
    public static MissionResDTO.MyInProgressMissionListDTO toMyInProgressMissionListDTO(
            Page<UserMissionStatus> page
    ) {
        List<MissionResDTO.MyInProgressMissionDTO> content = page.getContent().stream()
                .map(MissionConverter::toMyInProgressMissionDTO)
                .toList();   // ⭐ stream 사용

        return MissionResDTO.MyInProgressMissionListDTO.builder()
                .missionList(content)
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    public static MissionResDTO.MyInProgressMissionDTO toMyInProgressMissionDTO(
            UserMissionStatus ums
    ) {
        return MissionResDTO.MyInProgressMissionDTO.builder()
                .userMissionStatusId(ums.getId())
                .missionId(ums.getMission().getId())
                .storeName(ums.getMission().getStore().getName())
                .missionTitle(ums.getMission().getTitle())
                .status(ums.getStatus())
                .createdAt(ums.getCreatedAt())
                .build();
    }
}
