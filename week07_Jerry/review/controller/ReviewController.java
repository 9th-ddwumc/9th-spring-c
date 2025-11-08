package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.ReviewException;
import com.example.umc9th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/reviews/search")
    public ApiResponse<List<ReviewResponse>> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ){
        List<ReviewResponse> result = reviewQueryService.searchReview(query, type);
        if (result.isEmpty()) {
            throw new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND);
        }
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @GetMapping("/reviews/filter")
    public ApiResponse<List<ReviewResponse>> searchReviewByStoreAndRating(
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rating
    ) {
        if (rating != null && (rating < 1 || rating > 5)) {
            throw new ReviewException(ReviewErrorCode.REVIEW_INVALID_RATING);
        }

        List<ReviewResponse> result = reviewQueryService.searchReviewByStoreAndRating(storeName, rating);
        if (result.isEmpty()) {
            throw new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND);
        }
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
