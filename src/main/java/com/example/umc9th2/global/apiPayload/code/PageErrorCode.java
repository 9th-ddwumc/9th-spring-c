package com.example.umc9th2.global.apiPayload.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum PageErrorCode implements BaseErrorCode {

    INVALID_PAGE(HttpStatus.BAD_REQUEST, "PAGE400", "페이지 번호는 1 이상이어야 합니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    PageErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
