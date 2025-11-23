package com.example.umc9th2.domain.review.dto;

public record ReviewResponse(
        Long id,
        String content,
        double star,
        String storeName,
        String locationName
) {}
