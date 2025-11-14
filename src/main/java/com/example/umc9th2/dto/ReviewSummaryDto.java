package com.example.umc9th2.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ReviewSummaryDto {
    private final Long id;
    private final String content;
    private final Double star;
    private final String reply;
    private final String storeName;
    private final String userNickname;
    private final LocalDateTime createdAt;

    public ReviewSummaryDto(Long id, String content, Double star, String reply,
                            String storeName, String userNickname, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.star = star;
        this.reply = reply;
        this.storeName = storeName;
        this.userNickname = userNickname;
        this.createdAt = createdAt;
    }
}
