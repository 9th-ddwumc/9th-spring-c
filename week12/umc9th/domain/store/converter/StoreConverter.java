package com.example.umc9th.domain.store.converter;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.dto.req.StoreRequestDTO;
import com.example.umc9th.domain.store.dto.res.StoreResponseDTO;

public class StoreConverter {
    public static StoreResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return StoreResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .rating(review.getStar())
                .content(review.getContent())
                .build();
    }

    public static Review toReview(StoreRequestDTO.ReviewDTO request) {
        return Review.builder()
                .star(request.getRating())
                .content(request.getContent())
                .build();
    }
}
