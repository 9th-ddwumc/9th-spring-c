package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionSuccessCode implements BaseSuccessCode {
    CREATED(HttpStatus.CREATED, "MM201_1", "미션 도전 등록 성공"),
    ONGOING_MISSION_FETCHED(HttpStatus.OK, "MM200_2", "미션 조회 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}