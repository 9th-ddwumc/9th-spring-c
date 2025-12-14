package com.example.umc9th2.domain.review.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ReviewResponseDto {
    private Long reviewId;
    private Long storeId;
    private String storeName;
    private Long userId;
    private String memberName;
    private Float star;
    private String content;
    private List<String> photoUrls;
    private LocalDateTime createdAt;
}
