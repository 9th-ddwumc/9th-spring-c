package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;

    public MemberMissionResDTO.OngoingMissionListDTO findOngoingMissions(Long memberId, Integer page) {

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        Page<MemberMission> result =
                memberMissionRepository.findAllByMemberIdAndIsCompleteFalse(memberId, pageRequest);

        return MemberMissionConverter.toOngoingMissionListDTO(result);
    }
}
