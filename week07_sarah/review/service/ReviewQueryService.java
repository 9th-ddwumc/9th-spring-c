package com.example.umc9th2.domain.review.service;

import com.example.umc9th2.domain.review.entity.QReview;
import com.example.umc9th2.domain.review.entity.Review;
import com.example.umc9th2.domain.review.repository.ReviewQueryDsl;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewQueryDsl reviewQueryDsl;

    // 현재 로그인한 사용자의 리뷰 중에서 가게 이름 또는 별점대에 따라 필터링

    public List<Review> searchMyReviews(String storeName, String starRange, Long currentUserId) {

        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        // 로그인한 사용자 필터
        builder.and(review.member.id.eq(currentUserId));

        // 가게 이름 필터
        if (storeName != null && !storeName.isBlank()) {
            builder.and(review.store.name.contains(storeName)); // 부분 일치 검색
        }

        // 별점 필터
        if (starRange != null && !starRange.isBlank()) {
            switch (starRange) {
                case "5":
                    builder.and(review.star.eq(5.0F));
                    break;
                case "4":
                    builder.and(review.star.between(4.0F, 4.9F));
                    break;
                case "3":
                    builder.and(review.star.between(3.0F, 3.9F));
                    break;
                case "2":
                    builder.and(review.star.between(2.0F, 2.9F));
                    break;
                default:
                    break;
            }
        }

        // 실제 조회 실행
        return reviewQueryDsl.searchReview(builder);
    }
}
