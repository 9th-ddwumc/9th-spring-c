package com.example.umc9th2.global.apiPayload;

import com.example.umc9th2.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private final Boolean isSuccess;
    private final String code;
    private final String message;
    private final T result;

    // 성공 응답
    public static <T> ApiResponse<T> onSuccess(GeneralSuccessCode code, T result) {
        return new ApiResponse<>(
                true,
                code.getCode(),
                code.getMessage(),
                result
        );
    }

    // 실패 응답
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(
                false,
                code.getCode(),
                code.getMessage(),
                result
        );
    }
}