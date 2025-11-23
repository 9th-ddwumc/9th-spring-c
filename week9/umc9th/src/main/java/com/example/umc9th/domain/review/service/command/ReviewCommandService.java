package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;

public interface ReviewCommandService {
    // 리뷰 작성
    ReviewResDTO.CreateDTO writeReview(
            ReviewReqDTO.CreateDTO dto
    );
}