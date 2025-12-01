package com.example.umc9th2.global.apiPayload.exception;


import com.example.umc9th2.domain.member.exception.code.MemberErrorCode;

public class MemberException extends GeneralException {

    public MemberException(MemberErrorCode errorCode) {
        super(errorCode);
    }
}