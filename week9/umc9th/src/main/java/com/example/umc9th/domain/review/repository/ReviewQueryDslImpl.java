package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.QLocation;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.querydsl.core.types.Projections.constructor;

@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;

    @Override
    public List<Review> searchReview(
            Predicate predicate
    ){
        // JPA 세팅
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        // Q 클래스 선언
        QReview review = QReview.review;
        QStore store = QStore.store;
        QLocation location = QLocation.location;

        return queryFactory
                .selectFrom(review)
                .leftJoin(store).on(store.id.eq(review.store.id))
                .leftJoin(location).on(location.id.eq(store.location.id))
                .where(predicate)
                .orderBy(review.createdAt.desc())
                .fetch();
    }

    @Override
    public List<ReviewResponse> searchReviewByStoreAndRating(String storeName, Integer ratingBucket) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview r = QReview.review;
        QStore s = QStore.store;
        QLocation l = QLocation.location;

        BooleanBuilder builder = new BooleanBuilder();

        if (storeName != null && !storeName.isBlank()) {
            builder.and(s.name.contains(storeName));
        }

        if (ratingBucket != null) {
            if (ratingBucket == 5) builder.and(r.star.eq(5.0));
            else builder.and(r.star.goe((double) ratingBucket)
                    .and(r.star.lt(ratingBucket + 1.0)));
        }

        return queryFactory
                .select(constructor(ReviewResponse.class,
                        r.id,
                        r.content,
                        r.star,
                        s.name,
                        l.name))
                .from(r)
                .join(r.store, s)
                .join(s.location, l)
                .where(builder)
                .orderBy(r.createdAt.desc())
                .fetch();
    }
}