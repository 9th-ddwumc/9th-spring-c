package com.example.umc9th2.domain.review.repository;

import com.example.umc9th2.domain.review.dto.ReviewResponse;
import com.querydsl.core.types.Predicate;
import com.example.umc9th2.domain.review.entity.Review;
import java.util.List;

public interface ReviewQueryDsl {
    // 검색용 QueryDSL 메서드
    List<Review> searchReview(
            Predicate predicate
    );
}