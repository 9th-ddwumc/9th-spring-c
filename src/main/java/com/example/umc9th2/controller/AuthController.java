package com.example.umc9th2.controller;

import com.example.umc9th2.dto.UserReqDTO;
import com.example.umc9th2.dto.UserResDTO;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th2.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<UserResDTO.SignUpDTO> signUp(
            @RequestBody @Valid UserReqDTO.SignUpDTO dto
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                authService.signUp(dto)
        );
    }

    // JWT 로그인 (세션 미션에서는 안 써도 됨)
    @PostMapping("/login")
    public ApiResponse<UserResDTO.LoginDTO> login(
            @RequestBody @Valid UserReqDTO.LoginDTO dto
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                authService.login(dto)
        );
    }
}
