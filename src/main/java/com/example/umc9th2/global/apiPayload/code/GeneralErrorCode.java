package com.example.umc9th2.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode {

    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "ERROR400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "ERROR401", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "ERROR403", "접근 권한이 없습니다."),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR500", "서버 내부 오류입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    @Override
    public HttpStatus getStatus() { return status; }
    @Override
    public String getCode() { return code; }
    @Override
    public String getMessage() { return message; }
}
