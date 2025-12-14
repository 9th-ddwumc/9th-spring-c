package com.example.umc9th.domain.store.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode {
    STORE_REVIEW_CREATED(HttpStatus.OK, "STORE200_1", "리뷰가 성공적으로 작성되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
