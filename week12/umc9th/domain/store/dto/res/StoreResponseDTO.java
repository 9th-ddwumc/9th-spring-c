package com.example.umc9th.domain.store.dto.res;

import lombok.Builder;
import lombok.Getter;

public class StoreResponseDTO {

    @Builder
    @Getter
    public static class CreateReviewResultDTO {
        private Long reviewId;
        private Double rating;
        private String content;
    }
}
