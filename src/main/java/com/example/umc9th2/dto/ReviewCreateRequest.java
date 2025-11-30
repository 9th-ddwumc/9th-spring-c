package com.example.umc9th2.dto;

import com.example.umc9th2.global.validation.annotation.ExistStore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewCreateRequest {

    @ExistStore
    private Long storeId;

    @NotBlank
    private String content;

    @NotNull
    @Min(0) @Max(5)
    private Double star;
}
