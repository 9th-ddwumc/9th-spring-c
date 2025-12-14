package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    MISSION_LIST_FETCHED(HttpStatus.OK, "MISSION200_1", "미션 목록을 성공적으로 조회했습니다."),
    MEMBER_MISSION_LIST_FETCHED(HttpStatus.OK, "MISSION200_2", "회원의 진행 중인 미션 목록을 성공적으로 조회했습니다."),

    MISSION_CHALLENGED(HttpStatus.OK, "MISSION200_3", "미션 도전이 완료되었습니다."),
    MISSION_COMPLETED(HttpStatus.OK, "MISSION200_4", "미션을 성공적으로 완료했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
