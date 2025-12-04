package com.example.umc9th2.domain.member.repository;

import com.example.umc9th2.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByStoreIdOrderByCreatedAtDesc(Long storeId);
}