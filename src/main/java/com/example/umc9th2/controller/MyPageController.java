package com.example.umc9th2.controller;

import com.example.umc9th2.dto.MissionResDTO;
import com.example.umc9th2.dto.MyMissionCardDto;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th2.global.validation.annotation.ValidPage;
import com.example.umc9th2.service.MyPageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    // 내 미션(진행중/완료)
    @GetMapping("/{userId}/missions")
    public ResponseEntity<ApiResponse<Page<MyMissionCardDto>>> getMyMissions(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "1") @ValidPage Integer page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<MyMissionCardDto> result =
                myPageService.getMyMissions(userId, PageRequest.of(page - 1, 10));

        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onSuccess(code, result));
    }

    // 내가 진행중인 미션 목록 조회
    @Operation(
            summary = "[마이페이지] 내가 진행중인 미션 목록 조회",
            description = "userId 기준으로 MissionStatus.IN_PROGRESS 상태인 미션만 10개씩 페이지네이션하여 조회합니다."
    )
    @ApiResponses({})
    @GetMapping("/{userId}/missions/in-progress")
    public ResponseEntity<ApiResponse<MissionResDTO.MyInProgressMissionListDTO>> getMyInProgressMissions(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "1") @ValidPage Integer page
    ) {
        MissionResDTO.MyInProgressMissionListDTO result =
                myPageService.getMyInProgressMissions(userId, page);

        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onSuccess(code, result));
    }

    // 내 배지
    @GetMapping("/{userId}/awards")
    public ResponseEntity<ApiResponse<Object>> getMyAwards(@PathVariable Long userId) {
        Object result = myPageService.getMyAwards(userId);

        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onSuccess(code, result));
    }
}
