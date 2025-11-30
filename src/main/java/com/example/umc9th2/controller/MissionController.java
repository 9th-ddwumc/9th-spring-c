package com.example.umc9th2.controller;

import com.example.umc9th2.dto.HomeMissionCardDto;
import com.example.umc9th2.dto.MissionResDTO;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th2.global.validation.annotation.ValidPage;
import com.example.umc9th2.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // ✅ 기존: 지역별 미션 조회
    @Operation(summary = "지역별 오픈 미션 조회", description = "regionId 에 해당하는 지역의 오픈된 미션 목록을 페이지네이션해서 조회합니다.")
    @ApiResponses({})
    @GetMapping("/region/{regionId}")
    public ResponseEntity<ApiResponse<Page<HomeMissionCardDto>>> getMissionsByRegion(
            @PathVariable Long regionId,
            @RequestParam(defaultValue = "1") @ValidPage Integer page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<HomeMissionCardDto> result =
                missionService.getOpenMissionsByRegion(regionId, PageRequest.of(page - 1, 10));

        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onSuccess(code, result));
    }

    // ✅ 신규: 2번 API – 특정 가게의 미션 목록
    @Operation(summary = "특정 가게의 미션 목록 조회",
            description = "storeId 에 해당하는 가게의 미션을 10개씩 페이지네이션하여 조회합니다.")
    @ApiResponses({})
    @GetMapping("/store/{storeId}")
    public ResponseEntity<ApiResponse<MissionResDTO.StoreMissionListDTO>> getStoreMissions(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "1") @ValidPage Integer page
    ) {
        MissionResDTO.StoreMissionListDTO result =
                missionService.getStoreMissions(storeId, page);

        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onSuccess(code, result));
    }
}
