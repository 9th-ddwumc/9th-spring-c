package com.example.umc9th2.service;

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
}
