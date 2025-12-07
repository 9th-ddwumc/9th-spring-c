package com.example.umc9th2.domain.review.controller;

import com.example.umc9th2.domain.review.dto.res.ReviewResponseDto;
import com.example.umc9th2.domain.review.service.ReviewService;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th2.global.paging.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/my")
    @Operation(summary = "내 리뷰 목록 조회", description = "10개씩 페이징 처리됨")
    public ApiResponse<Page<ReviewResponseDto>> getMyReviews(
            @RequestParam Long memberId,
            @ValidPage Integer page
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviewService.getMyReviews(memberId, page)
        );
    }
}