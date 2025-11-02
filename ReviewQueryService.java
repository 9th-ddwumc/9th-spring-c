package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RestController
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    // 내가 작성한 리뷰 보기 API
    @GetMapping("/reviews/my")
    public List<Review> getMyReviews(
            @RequestParam(required = false) String storeName, // ex. 반이학생마라탕마라반
            @RequestParam(required = false) String starRange  // ex. "5", "4", "3"
    ) {
        // QueryDSL 실행
        return searchReview(storeName, starRange);
    }

    // 실제 QueryDSL 처리
    public List<Review> searchReview(String storeName, String starRange) {

        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        // 가게 이름 필터링
        if (storeName != null && !storeName.isEmpty()) {
            builder.and(review.store.name.contains(storeName));
        }

        // 별점 구간 필터링
        if (starRange != null && !starRange.isEmpty()) {
            switch (starRange) {
                case "5": // 5점대
                    builder.and(review.star.goe(4.5f));
                    break;
                case "4": // 4점대
                    builder.and(review.star.between(3.5f, 4.49f));
                    break;
                case "3": // 3점대
                    builder.and(review.star.between(2.5f, 3.49f));
                    break;
                case "2": // 2점대
                    builder.and(review.star.between(1.5f, 2.49f));
                    break;
                case "1": // 1점대
                    builder.and(review.star.between(0.5f, 1.49f));
                    break;
                default:
                    break;
            }
        }

        // 최종 조회 실행
        List<Review> reviewList = reviewRepository.searchReview(builder);
        return reviewList;
    }

    public List<Review> searchReviewsByStore(String storeName) {
        return searchReview(storeName, null);
    }

}
