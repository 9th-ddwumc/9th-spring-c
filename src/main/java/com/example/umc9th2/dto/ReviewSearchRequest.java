package com.example.umc9th2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewSearchRequest {
    private Long userId;
    private String store;
    private Integer starBand;
    private String sort = "recent"; // recent | rating
}
