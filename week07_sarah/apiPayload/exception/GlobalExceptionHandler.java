package com.example.umc9th2.global.apiPayload.exception;

import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th2.global.apiPayload.code.GeneralErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 커스텀 예외 (ReviewException 등)
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(GeneralException ex) {

        return ResponseEntity.status(ex.getCode().getStatus())
                .body(ApiResponse.onFailure(
                        ex.getCode(),
                        null
                ));
    }

    // 예기치 못한 서버 에러
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {

        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(
                        code,
                        ex.getMessage()
                ));
    }
}
