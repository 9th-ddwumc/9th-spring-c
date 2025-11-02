package com.example.umc9th2.repository;

import com.example.umc9th2.dto.ReviewSearchRequest;
import com.example.umc9th2.dto.ReviewSummaryDto;
import com.example.umc9th2.domain.review.QReview;
import com.example.umc9th2.domain.store.QStore;
import com.example.umc9th2.domain.user.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory query;

    public ReviewRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Page<ReviewSummaryDto> searchMyReviews(ReviewSearchRequest req, Pageable pageable) {
        QReview review = QReview.review;
        QUser user = QUser.user;
        QStore store = QStore.store;

        BooleanBuilder where = new BooleanBuilder();

        where.and(req.getUserId() != null ?
                review.user.id.eq(req.getUserId()) : review.id.isNull()); // 미션: 내가 작성한

        if (StringUtils.hasText(req.getStore())) {
            where.and(store.name.containsIgnoreCase(req.getStore().trim()));
        }

        if (req.getStarBand() != null) {
            double min = req.getStarBand();
            double max = req.getStarBand() + 1.0;
            where.and(review.star.goe(min).and(review.star.lt(max)));
        }

        var order = "rating".equalsIgnoreCase(req.getSort())
                ? review.star.desc().nullsLast()
                : review.createdAt.desc().nullsLast();

        List<ReviewSummaryDto> results = query
                .select(Projections.constructor(ReviewSummaryDto.class,
                        review.id,
                        review.content,
                        review.star,
                        review.reply,
                        store.name,
                        user.nickname,
                        review.createdAt))
                .from(review)
                .join(review.user, user)
                .join(review.store, store)
                .where(where)
                .orderBy(order)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = query
                .select(review.count())
                .from(review)
                .join(review.user, user)
                .join(review.store, store)
                .where(where)
                .fetchOne();

        return new PageImpl<>(results, pageable, total == null ? 0 : total);
    }
}
