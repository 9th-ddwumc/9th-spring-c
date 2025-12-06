package com.example.umc9th2.domain.review.controller;

import com.example.umc9th2.domain.review.entity.Review;
import com.example.umc9th2.domain.review.exception.ReviewErrorCode;
import com.example.umc9th2.domain.review.exception.ReviewException;
import com.example.umc9th2.domain.review.service.ReviewQueryService;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewQueryController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/reviews/filter")
    public ApiResponse<List<Review>> searchMyReviews(
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) String starRange,
            @RequestParam Long currentUserId
    ) {
        // 별점 입력값 검증
        if (starRange != null && !starRange.isBlank()) {
            try {
                int star = Integer.parseInt(starRange);
                if (star < 1 || star > 5) {
                    throw new ReviewException(ReviewErrorCode.INVALID_STAR_VALUE);
                }
            } catch (NumberFormatException e) {
                throw new ReviewException(ReviewErrorCode.INVALID_STAR_FORMAT);
            }
        }

        List<Review> result = reviewQueryService.searchMyReviews(storeName, starRange, currentUserId);

        if (result.isEmpty()) {
            throw new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND);
        }

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
