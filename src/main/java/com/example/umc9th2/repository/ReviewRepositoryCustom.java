package com.example.umc9th2.repository;

import com.example.umc9th2.dto.ReviewSearchRequest;
import com.example.umc9th2.dto.ReviewSummaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
    Page<ReviewSummaryDto> searchMyReviews(ReviewSearchRequest req, Pageable pageable);
}
