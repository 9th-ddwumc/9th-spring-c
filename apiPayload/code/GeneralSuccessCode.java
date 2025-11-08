package com.example.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode{

    GOOD_REQUEST(HttpStatus.ACCEPTED,
            "COMMON400_2",
            "성공했습니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
