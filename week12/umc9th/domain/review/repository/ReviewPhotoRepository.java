package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.entity.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewPhotoRepository extends JpaRepository<ReviewPhoto, Long> {

    List<ReviewPhoto> findAllByReview(Review review);
}
