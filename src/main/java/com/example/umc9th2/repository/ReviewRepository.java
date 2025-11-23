package com.example.umc9th2.repository;

import com.example.umc9th2.domain.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

    // 내가 쓴 리뷰만 페이징 조회
    Page<Review> findByUserId(Long userId, Pageable pageable);
}
