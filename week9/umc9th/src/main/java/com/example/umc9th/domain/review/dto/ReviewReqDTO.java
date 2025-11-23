package com.example.umc9th.domain.review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReviewReqDTO {

    public record CreateDTO (
            Long storeId,
            double star,
            String content

            // OPTIONAL
            // @JsonProperty("photo_urls")
            // List<String> photoUrls
    ) {}
}