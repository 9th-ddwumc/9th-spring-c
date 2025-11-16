package com.example.umc9th.domain.review.dto;

import lombok.Builder;
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
}
