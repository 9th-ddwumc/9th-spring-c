// TODO: Chapter7 응답 통일 테스트
package com.example.umc9th2.controller;

import com.example.umc9th2.dto.ReviewSearchRequest;
import com.example.umc9th2.dto.ReviewSummaryDto;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th2.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewQueryController {

    private final ReviewQueryService service;

    // 내가 쓴 리뷰 조회
    // GET /api/reviews/my?userId=1&store=...&starBand=...&sort=recent&page=0&size=10
    @GetMapping("/my")
    public ResponseEntity<ApiResponse<Page<ReviewSummaryDto>>> getMyReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) String store,
            @RequestParam(required = false) Integer starBand,
            @RequestParam(defaultValue = "recent") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        ReviewSearchRequest req = new ReviewSearchRequest();
        req.setUserId(userId);
        req.setStore(store);
        req.setStarBand(starBand);
        req.setSort(sort);

        Page<ReviewSummaryDto> result = service.searchMyReviews(req, page, size);

        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onSuccess(code, result));
    }
}
