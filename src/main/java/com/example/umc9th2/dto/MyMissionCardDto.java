package com.example.umc9th2.dto;

import java.time.LocalDateTime;

public record MyMissionCardDto(
        Long umcId,
        Long missionId,
        String storeName,
        String missionTitle,
        LocalDateTime createdAt
) {}
