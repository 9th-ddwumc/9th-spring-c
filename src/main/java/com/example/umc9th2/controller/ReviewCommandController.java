package com.example.umc9th2.controller;

import com.example.umc9th2.dto.ReviewCreateRequest;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th2.service.ReviewCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewCommandController {

    private final ReviewCommandService reviewCommandService;

    // ✅ 미션 2번: 가게에 리뷰 추가하기
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createReview(
            @RequestBody @Valid ReviewCreateRequest request
    ) {
        Long reviewId = reviewCommandService.createReview(request);
        GeneralSuccessCode code = GeneralSuccessCode.OK; // CREATED 있으면 그걸 써도 됨

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onSuccess(code, reviewId));
    }
}
