package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
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
    public List<Review> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ){
        List<Review> result = reviewQueryService.searchReview(query, type);
        return result;
    }

    @GetMapping("/reviews/filter")
    public List<ReviewResponse> searchReviewByStoreAndRating(
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rating
    ) {
        return reviewQueryService.searchReviewByStoreAndRating(storeName, rating);
    }
}
