package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    default Review createReview(Review review) {
        return save(review);
    }

    // 가게에 달린 리뷰 보기
    List<Review> findAllByStoreIdOrderByCreatedAtDesc(Long storeId);
}
