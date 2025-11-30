package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.MemberMission;
import org.springframework.data.domain.Page;

public class MemberMissionConverter {

    public static MemberMissionResDTO.ChallengeDTO toChallengeDTO(MemberMission memberMission) {
        return MemberMissionResDTO.ChallengeDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .storeId(memberMission.getMission().getStore().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .point(memberMission.getMission().getPoint())
                .isComplete(memberMission.isComplete())
                .build();
    }

    public static MemberMissionResDTO.OngoingMissionListDTO toOngoingMissionListDTO(Page<MemberMission> result) {
        return MemberMissionResDTO.OngoingMissionListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MemberMissionConverter::toOngoingMissionDTO)
                        .toList())
                .listSize(result.getNumberOfElements())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MemberMissionResDTO.OngoingMissionDTO toOngoingMissionDTO(MemberMission mm) {
        return MemberMissionResDTO.OngoingMissionDTO.builder()
                .memberMissionId(mm.getId())
                .missionId(mm.getMission().getId())
                .storeName(mm.getMission().getStore().getName())
                .cost(mm.getMission().getCost())
                .point(mm.getMission().getPoint())
                .isComplete(mm.isComplete())
                .build();
    }
}