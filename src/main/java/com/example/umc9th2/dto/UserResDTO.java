package com.example.umc9th2.dto;

import lombok.Builder;

public class UserResDTO {

    // 회원가입 응답
    @Builder
    public record SignUpDTO(
            Long userId,
            String email,
            String nickname
    ) {}

    // 로그인 응답 (세션에서는 accessToken=null, JWT에서는 토큰 채워서 사용)
    @Builder
    public record LoginDTO(
            Long userId,
            String email,
            String nickname,
            String accessToken
    ) {}
}
