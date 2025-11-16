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

    public List<Review> searchMyReviews(String storeName, String starRange, Long currentUserId) {

        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.member.id.eq(currentUserId));

        if (storeName != null && !storeName.isBlank()) {
            builder.and(review.store.name.eq(storeName));
        }

        if (starRange != null) {
            switch (starRange) {
                case "5":
                    builder.and(review.star.eq(5.0F));
                    break;
                case "4":
                    builder.and(review.star.between(4.0, 4.9));
                    break;
                case "3":
                    builder.and(review.star.between(3.0, 3.9));
                    break;
                case "2":
                    builder.and(review.star.between(2.0, 2.9));
                    break;
                default:
                    break;
            }
        }

        return reviewQueryDsl.searchReview(builder);
    }
}
