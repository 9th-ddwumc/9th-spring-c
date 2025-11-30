package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "해당 미션을 찾을 수 없습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_2", "해당 가게를 찾을 수 없습니다."),
    MEMBER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_3", "해당 회원의 미션 기록을 찾을 수 없습니다."),

    ALREADY_CHALLENGED(HttpStatus.BAD_REQUEST, "MISSION400_1", "이미 도전 중인 미션입니다."),
    ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MISSION400_2", "이미 완료된 미션입니다."),

    INVALID_MISSION_REQUEST(HttpStatus.BAD_REQUEST, "MISSION400_3", "요청한 미션 정보가 올바르지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
