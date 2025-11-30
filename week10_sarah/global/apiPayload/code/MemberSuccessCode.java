package com.example.umc9th2.global.apiPayload.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MemberSuccessCode implements BaseErrorCode {

    MEMBER_CREATED(HttpStatus.CREATED, "MEMBER201", "회원가입이 완료되었습니다."),
    MEMBER_LOGIN(HttpStatus.OK, "MEMBER200", "로그인 성공"),
    FOUND(HttpStatus.OK, "MEMBER200", "요청이 성공적으로 처리되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    MemberSuccessCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }
}
