package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.exception.ReviewException;
import com.example.umc9th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.annotation.PageParam;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    // 나의 리뷰 조회
    @Operation(
            summary = "내가 작성한 리뷰 목록 조회 API By 제리",
            description = "내가 작성한 리뷰 목록을 조회합니다. 페이지네이션으로 제공합니다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/reviews/my")
    public ApiResponse<ReviewResDTO.ReviewMyPreviewListDTO> getMyReviews(
            @RequestParam Long memberId,
            @PageParam Integer page
    ) {
        ReviewSuccessCode code = ReviewSuccessCode.REVIEW_FETCHED;
        return ApiResponse.onSuccess(code, reviewQueryService.findMyReview(memberId, page));
    }


}