package com.example.umc9th2.global.apiPayload.exception;

import com.example.umc9th2.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GeneralException extends RuntimeException {
    private final BaseErrorCode code;
}
