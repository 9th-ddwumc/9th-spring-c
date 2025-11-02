package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {

    // ✅ 지역만 조회
    @Query(
            value = "SELECT r1.* " +
                    "FROM review r1 " +
                    "LEFT JOIN store s1 ON r1.store_id = s1.id " +
                    "LEFT JOIN location l1 ON s1.location_id = l1.id " +
                    "WHERE l1.name LIKE CONCAT('%', :name, '%')",
            nativeQuery = true
    )
    List<Review> searchReviewByLocation(@Param("name") String name);


    // ✅ 별점만 조회
    @Query(
            value = "SELECT r1.* " +
                    "FROM review r1 " +
                    "LEFT JOIN store s1 ON r1.store_id = s1.id " +
                    "LEFT JOIN location l1 ON s1.location_id = l1.id " +
                    "WHERE r1.star > :star",
            nativeQuery = true
    )
    List<Review> searchReviewByStar(@Param("star") Float star);


    // ✅ 지역 + 별점 조회
    @Query(
            value = "SELECT r1.* " +
                    "FROM review r1 " +
                    "LEFT JOIN store s1 ON r1.store_id = s1.id " +
                    "LEFT JOIN location l1 ON s1.location_id = l1.id " +
                    "WHERE l1.name LIKE CONCAT('%', :name, '%') " +
                    "AND r1.star > :star",
            nativeQuery = true
    )
    List<Review> searchReviewByLocationAndStar(@Param("name") String name, @Param("star") Float star);
}
