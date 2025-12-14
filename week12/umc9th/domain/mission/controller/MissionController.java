package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.query.MissionQueryService;
import com.example.umc9th.global.annotation.PageParam;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionQueryService missionQueryService;

    @Operation(summary = "특정 가게의 미션 목록 조회", description = "storeId 기준으로 미션을 10개씩 페이징 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/missions")
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getStoreMissions(
            @RequestParam Long storeId,
            @PageParam Integer page
    ) {
        MissionSuccessCode code = MissionSuccessCode.MISSION_LIST_FETCHED;
        return ApiResponse.onSuccess(code, missionQueryService.findMissionsByStore(storeId, page));
    }
}
