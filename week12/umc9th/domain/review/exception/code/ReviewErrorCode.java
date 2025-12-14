package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    // 400 BAD REQUEST
    REVIEW_INVALID_RATING(HttpStatus.BAD_REQUEST, "REVIEW400_1", "별점은 1~5 사이여야 합니다."),
    REVIEW_INVALID_QUERY_TYPE(HttpStatus.BAD_REQUEST, "REVIEW400_2", "잘못된 검색 타입입니다."),

    // 404 NOT FOUND
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "리뷰를 찾을 수 없습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_2", "가게를 찾을 수 없습니다."),

    // 409 CONFLICT
    REVIEW_ALREADY_EXISTS(HttpStatus.CONFLICT, "REVIEW409_1", "이미 해당 가게에 리뷰를 작성했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}