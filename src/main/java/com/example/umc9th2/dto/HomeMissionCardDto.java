package com.example.umc9th2.dto;

import java.time.LocalDateTime;

public record HomeMissionCardDto(
        Long missionId,
        String missionTitle,
        String storeName,
        Long regionId,
        LocalDateTime createdAt
) {}