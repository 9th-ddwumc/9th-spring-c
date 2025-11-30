package com.example.umc9th.global.apiPayload.exception;

import com.example.umc9th.global.apiPayload.code.PageErrorCode;

public class PageException extends GeneralException {
    public PageException(PageErrorCode code) {
        super(code);
    }
}
