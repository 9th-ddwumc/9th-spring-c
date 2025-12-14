package com.example.umc9th.domain.store.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    public static class ReviewDTO {
        @NotNull
        private Double rating;

        @NotBlank
        private String content;
    }
}
