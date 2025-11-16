package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.exception.ReviewException;
import com.example.umc9th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

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

    // 리뷰 작성
    @PostMapping("/reviews")
    public ApiResponse<ReviewResDTO.CreateDTO> writeReview(
            @RequestBody ReviewReqDTO.CreateDTO dto
            ){
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED, reviewCommandService.writeReview(dto));
    }
}
