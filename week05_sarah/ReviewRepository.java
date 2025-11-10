package com.example.umc9th2.domain.member.repository;

import com.example.umc9th2.domain.review.entity.Review;
import com.example.umc9th2.domain.store.entity.Store;
import com.example.umc9th2.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByStoreIdOrderByCreatedAtDesc(Long storeId);
}