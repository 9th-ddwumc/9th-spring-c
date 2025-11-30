package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th.domain.mission.dto.MemberMissionReqDTO;
import com.example.umc9th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.exception.code.MemberMissionSuccessCode;
import com.example.umc9th.domain.mission.service.MemberMissionService;
import com.example.umc9th.domain.mission.service.query.MemberMissionQueryService;
import com.example.umc9th.global.annotation.PageParam;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
public class MemberMissionController {
    private final MemberMissionService memberMissionService;
    private final MemberMissionQueryService memberMissionQueryService;

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

    @Operation(summary = "내가 진행중인 미션 목록 조회", description = "memberId 기준으로 isComplete=false 미션을 10개씩 페이징 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/ongoing")
    public ApiResponse<MemberMissionResDTO.OngoingMissionListDTO> getOngoingMissions(
            @RequestParam Long memberId,
            @PageParam Integer page
    ) {
        MemberMissionSuccessCode code = MemberMissionSuccessCode.ONGOING_MISSION_FETCHED;
        return ApiResponse.onSuccess(
                code,
                memberMissionQueryService.findOngoingMissions(memberId, page)
        );
    }
}