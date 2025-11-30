package com.example.umc9th.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PageErrorCode implements BaseErrorCode {

    PAGE_TOO_SMALL(HttpStatus.BAD_REQUEST, "PAGE400", "page는 1 이상이어야 합니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
