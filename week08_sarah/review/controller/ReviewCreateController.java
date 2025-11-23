package com.example.umc9th2.domain.review.controller;

import com.example.umc9th2.domain.review.dto.req.ReviewCreateRequestDto;
import com.example.umc9th2.domain.review.dto.res.ReviewCreateResponseDto;
import com.example.umc9th2.domain.review.service.ReviewService;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewCreateController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ApiResponse<ReviewCreateResponseDto>> createReview(
            @RequestBody ReviewCreateRequestDto requestDto
    ) {
        ReviewCreateResponseDto response = reviewService.createReview(requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.onSuccess(GeneralSuccessCode.OK, response));
    }
}