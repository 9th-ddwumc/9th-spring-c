package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.QLocation;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;

    public List<Review> searchReview(String query, String type) {
        // Q 클래스 정의
        QReview review = QReview.review;
        QLocation location = QLocation.location;

        // BolleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // BooleanBuilder 사용

        // 동적 쿼리: 검색 조건
        if (type.equals("location")){
            builder.and(location.name.contains(query));
        }
        if (type.equals("star")){
            builder.and(review.star.goe(Float.parseFloat(query)));
        }
        if (type.equals("both")){
            // & 기준 변환
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            // 동적 쿼리
            builder.and(location.name.contains(firstQuery));
            builder.and(review.star.goe(Double.parseDouble(secondQuery)));
        }

        // Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);

        // 리턴
        return reviewList;
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> searchReviewByStoreAndRating(String storeName, Integer ratingBucket) {
        return reviewRepository.searchReviewByStoreAndRating(storeName, ratingBucket);
    }
}
