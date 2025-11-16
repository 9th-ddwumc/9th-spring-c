package com.example.umc9th2.global.apiPayload.status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReasonDto {
    private Boolean isSuccess;
    private String code;
    private String message;
}