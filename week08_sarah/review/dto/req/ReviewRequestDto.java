package com.example.umc9th2.domain.review.dto.req;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewRequestDto {
    private Long storeId;
    private Long userId;
    private Float star;
    private String content;
    private List<String> photoUrls;
}
