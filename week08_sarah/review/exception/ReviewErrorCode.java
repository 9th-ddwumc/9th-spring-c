package com.example.umc9th2.domain.review.exception;

import com.example.umc9th2.global.apiPayload.code.BaseErrorCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ReviewErrorCode implements BaseErrorCode {
    // 400 BAD REQUEST
    INVALID_STAR_VALUE(HttpStatus.BAD_REQUEST, "REVIEW400_1", "별점은 1~5 사이여야 합니다."),
    INVALID_STAR_FORMAT(HttpStatus.BAD_REQUEST, "REVIEW400_2", "별점은 숫자 형식이어야 합니다."),

    // 404 NOT FOUND
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "요청하신 조건에 맞는 리뷰를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ReviewErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}