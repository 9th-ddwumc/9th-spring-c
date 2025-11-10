package com.example.umc9th2.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode {
    OK(HttpStatus.OK, "SUCCESS200", "요청이 성공적으로 처리되었습니다."),
    CREATED(HttpStatus.CREATED, "SUCCESS201", "리소스가 성공적으로 생성되었습니다."),
    READ_SUCCESS(HttpStatus.OK, "SUCCESS200", "데이터 조회에 성공했습니다."),
    UPDATE_SUCCESS(HttpStatus.OK, "SUCCESS200", "데이터 수정에 성공했습니다."),
    DELETE_SUCCESS(HttpStatus.OK, "SUCCESS200", "데이터 삭제에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
