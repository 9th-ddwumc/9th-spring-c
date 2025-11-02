package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/reviews/my")
    public List<Review> getMyReviews(
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) String starRange
    ) {
        return reviewQueryService.searchReview(storeName, starRange);
    }

}
