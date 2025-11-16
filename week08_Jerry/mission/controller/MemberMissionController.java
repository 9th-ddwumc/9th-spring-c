package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th.domain.mission.dto.MemberMissionReqDTO;
import com.example.umc9th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.exception.code.MemberMissionSuccessCode;
import com.example.umc9th.domain.mission.service.MemberMissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
public class MemberMissionController {
    private final MemberMissionService memberMissionService;

    @PostMapping
    public ApiResponse<MemberMissionResDTO.ChallengeDTO> challengeMission(
            @Valid @RequestBody MemberMissionReqDTO.ChallengeDTO request
            ){
        Long memberId = 1L;

        MemberMission memberMission = memberMissionService.challengeMission(memberId, request.missionId());
        return ApiResponse.onSuccess(
                MemberMissionSuccessCode.CREATED,
                MemberMissionConverter.toChallengeDTO(memberMission)
        );
    }
}
