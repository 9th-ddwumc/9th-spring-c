package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {

    default Review createReview(Review review) {
        return save(review);
    }

    // 가게에 달린 리뷰 보기
    List<Review> findAllByStoreIdOrderByCreatedAtDesc(Long storeId);

    // 지역만 조회
    @Query(
            value = "SELECT r1.* " +
                    "FROM review r1 " +
                    "LEFT JOIN store s1 ON r1.store_id = s1.id " +
                    "LEFT JOIN location l1 on s1.location_id = l1.id " +
                    "WHERE l1.name LIKE CONCAT('%', :name, '%')", nativeQuery = true
    )
    List<Review> searchReviewByLocation(@Param("name") String name);

    @Query(
            value = "SELECT r1.* " +
                    "FROM review r1 " +
                    "LEFT JOIN store s1 ON r1.store_id = s1.id " +
                    "LEFT JOIN location l1 on s1.location_id = l1.id " +
                    "WHERE r1.star > :star ", nativeQuery = true
    )
    List<Review> searchReviewByStar(
            @Param("star") Float star
    );

    Page<Review> findAllByMemberId(Long memberId, Pageable pageable);


}