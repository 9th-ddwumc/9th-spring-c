package com.example.umc9th2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserReqDTO {

    // 회원가입 요청
    public record SignUpDTO(
            @Email
            String email,
            @NotBlank
            String password,
            @NotBlank
            String nickname
    ) {}

    // 로그인 요청
    public record LoginDTO(
            @Email
            String email,
            @NotBlank
            String password
    ) {}
}
