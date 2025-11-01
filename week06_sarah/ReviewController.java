package com.example.umc9th2.domain.review.controller;

import com.example.umc9th2.domain.review.entity.Review;
import com.example.umc9th2.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/my")
    public List<Review> getMyReviews(
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) String starRange
    ) {
        // 로그인 기능이 아직 없으므로 임시로 userId=1L 설정
        Long currentUserId = 1L;

        return reviewQueryService.searchMyReviews(storeName, starRange, currentUserId);
    }
}
