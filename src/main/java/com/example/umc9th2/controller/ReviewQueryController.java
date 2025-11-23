package com.example.umc9th2.controller;

import com.example.umc9th2.dto.ReviewResDTO;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th2.global.validation.annotation.ValidPage;
import com.example.umc9th2.service.ReviewQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewQueryController {

    // ❗ 기존 private final ReviewQueryService service; 같은 필드는 전부 지우고 이거 하나만
    private final ReviewQueryService reviewQueryService;

    /**
     * 미션 API 1 — 내가 작성한 리뷰 목록 조회
     * - userId: 조회할 유저 ID
     * - page: 1 이상, 기본값 1
     */
    @Operation(
            summary = "[미션] 내가 작성한 리뷰 목록 조회",
            description = "특정 사용자가 작성한 리뷰를 페이지네이션으로 조회합니다. page는 1부터 시작합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "리뷰 목록 조회 성공"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "잘못된 page 파라미터"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "서버 내부 에러"
            )
    })

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<ReviewResDTO.ReviewPreViewListDTO>> getMyReviews(
            @RequestParam Long userId,
            @RequestParam(defaultValue = "1") @ValidPage Integer page
    ) {
        //  파라미터 실제 사용
        ReviewResDTO.ReviewPreViewListDTO result =
                reviewQueryService.getMyReviews(userId, page);

        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onSuccess(code, result));
    }
}
