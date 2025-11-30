package com.example.umc9th2.service;

import com.example.umc9th2.converter.ReviewConverter;
import com.example.umc9th2.domain.review.Review;
import com.example.umc9th2.dto.ReviewResDTO;
import com.example.umc9th2.dto.ReviewSearchRequest;
import com.example.umc9th2.dto.ReviewSummaryDto;
import com.example.umc9th2.repository.ReviewRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;

    public ReviewQueryService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Page<ReviewSummaryDto> searchMyReviews(ReviewSearchRequest req, int page, int size) {
        return reviewRepository.searchMyReviews(req, PageRequest.of(page, size));
    }

    // API 1 – 내가 작성한 리뷰 목록
    // page 는 1부터 들어온다고 가정하고, 내부에서 0-based 로 변환한다.
    // 한 페이지는 10개 고정.
    public ReviewResDTO.ReviewPreViewListDTO getMyReviews(Long userId, int page) {
        // page: 1 → 0, 2 → 1 ...
        PageRequest pageable = PageRequest.of(page - 1, 10);

        Page<Review> result = reviewRepository.findByUserId(userId, pageable);

        // 엔티티 → DTO 변환
        return ReviewConverter.toReviewPreviewListDTO(result);
    }
}
