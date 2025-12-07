package com.example.umc9th2.domain.test.exception;

import com.example.umc9th2.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th2.global.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code);
    }
}
