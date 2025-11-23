package com.example.umc9th2.converter;

import com.example.umc9th2.domain.review.Review;
import com.example.umc9th2.dto.ReviewResDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public class ReviewConverter {

    // Page<Review> -> ReviewPreViewListDTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ) {
        List<ReviewResDTO.ReviewPreViewDTO> content = result.getContent().stream()
                .map(ReviewConverter::toReviewPreviewDTO)
                .toList(); // ⭐ for문 금지 조건 → stream 사용

        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(content)
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    // Review -> ReviewPreViewDTO
    public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(Review review) {

        LocalDate createdDate = review.getCreatedAt() != null
                ? review.getCreatedAt().toLocalDate()
                : null;

        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getUser().getNickname())
                .score(review.getStar())
                .body(review.getContent())
                .createdAt(createdDate)
                .build();
    }
}
