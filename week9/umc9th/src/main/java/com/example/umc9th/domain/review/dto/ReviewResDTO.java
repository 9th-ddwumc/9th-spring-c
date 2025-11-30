package com.example.umc9th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateDTO(
            Long reviewId,
            double star,
            String content,
            String storeName
            // List<String> photoUrls
    ) {}

    @Builder
    public record ReviewMyPreviewDTO(
            String storeName,
            Float score,
            String body,
            LocalDate createdAt
    ) {}

    @Builder
    public record ReviewMyPreviewListDTO(
            List<ReviewMyPreviewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}
}