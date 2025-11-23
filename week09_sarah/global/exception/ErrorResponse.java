// 예외 발생 시 클라이언트에게 전달되는 공통 에러 응답 형식
package com.example.umc9th2.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private int status;      // HTTP 상태 코드
    private String message;  // 에러 메시지
}
