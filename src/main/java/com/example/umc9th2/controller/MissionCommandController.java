package com.example.umc9th2.controller;

import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th2.global.validation.annotation.ExistStore;
import com.example.umc9th2.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class MissionCommandController {

    private final MissionService missionService;

    /**
     * ✅ 가게의 미션을 도전 중인 미션에 추가 (미션 도전하기)
     * POST /api/stores/{storeId}/missions/{missionId}/challenge
     */
    @PostMapping("/{storeId}/missions/{missionId}/challenge")
    public ResponseEntity<ApiResponse<Long>> challengeMission(
            @PathVariable @ExistStore Long storeId,
            @PathVariable Long missionId
    ) {
        Long userMissionStatusId = missionService.challengeMission(storeId, missionId);

        GeneralSuccessCode code = GeneralSuccessCode.OK; // CREATED 코드가 따로 있으면 그걸 써도 됨

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onSuccess(code, userMissionStatusId));
    }
}
