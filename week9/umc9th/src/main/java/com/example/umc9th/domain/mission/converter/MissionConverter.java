package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

public class MissionConverter {

    public static MissionResDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<Mission> result) {
        return MissionResDTO.MissionPreviewListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toMissionPreviewDTO)
                        .toList())
                .listSize(result.getNumberOfElements())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        return MissionResDTO.MissionPreviewDTO.builder()
                .missionId(mission.getId())
                .cost(mission.getCost())
                .reward(mission.getPoint()) // 없으면 제거
                .deadline(mission.getDeadline()) // 타입 맞게
                .build();
    }
}
