package com.example.umc9th2.domain.mission.controller;

import com.example.umc9th2.domain.mission.dto.req.MissionChallengeRequestDto;
import com.example.umc9th2.domain.mission.dto.res.MissionChallengeResponseDto;
import com.example.umc9th2.domain.mission.dto.res.MissionCompleteResponseDto;
import com.example.umc9th2.domain.mission.dto.res.MissionInProgressDto;
import com.example.umc9th2.domain.mission.service.MissionChallengeService;
import com.example.umc9th2.domain.mission.service.MissionService;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th2.global.paging.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionChallengeService missionChallengeService;
    private final MissionService missionService;

    // 1) 미션 도전하기 API
    @PostMapping("/challenge")
    @Operation(summary = "미션 도전하기")
    public ApiResponse<MissionChallengeResponseDto> challengeMission(
            @RequestBody MissionChallengeRequestDto requestDto
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionChallengeService.challengeMission(requestDto)
        );
    }

    // 2) 내 진행중인 미션 조회 (페이징)
    @GetMapping("/in-progress")
    @Operation(summary = "내 진행중인 미션 조회", description = "10개씩 페이징 처리됨")
    public ApiResponse<Page<MissionInProgressDto>> getInProgressMissions(
            @RequestParam Long memberId,
            @ValidPage Integer page
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionService.getMyInProgressMissions(memberId, page)
        );
    }

    // 3) 미션 완료 처리 (추가된 API)
    @PatchMapping("/{missionChallengeId}/complete")
    @Operation(summary = "미션 완료 처리", description = "완료된 상태로 수정 + 완료 날짜 반환")
    public ApiResponse<MissionCompleteResponseDto> completeMission(
            @PathVariable Long missionChallengeId
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionService.completeMission(missionChallengeId)
        );
    }

}
