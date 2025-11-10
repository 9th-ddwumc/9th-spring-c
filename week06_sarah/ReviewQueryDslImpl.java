package com.example.umc9th2.domain.review.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.example.umc9th2.domain.review.entity.QReview;
import com.example.umc9th2.domain.review.entity.Review;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;

    @Override
    public List<Review> searchReview(Predicate predicate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .leftJoin(review.replies).fetchJoin()
                .leftJoin(review.photos).fetchJoin()
                .where(predicate)
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}
