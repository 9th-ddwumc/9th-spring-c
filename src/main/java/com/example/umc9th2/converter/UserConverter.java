package com.example.umc9th2.converter;

import com.example.umc9th2.domain.user.User;
import com.example.umc9th2.dto.UserReqDTO;
import com.example.umc9th2.dto.UserResDTO;
import com.example.umc9th2.global.auth.enums.UserRole;

public class UserConverter {

    // 회원가입 DTO + 암호화된 비밀번호 + 역할 -> User 엔티티
    public static User toUser(
            UserReqDTO.SignUpDTO dto,
            String encodedPassword,
            UserRole role
    ) {
        return new User(
                dto.email(),
                encodedPassword,
                dto.nickname(),
                role
        );
    }

    // User -> 회원가입 응답 DTO
    public static UserResDTO.SignUpDTO toSignUpDTO(User user) {
        return UserResDTO.SignUpDTO.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }

    // User + accessToken -> 로그인 응답 DTO
    public static UserResDTO.LoginDTO toLoginDTO(User user, String accessToken) {
        return UserResDTO.LoginDTO.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .accessToken(accessToken)
                .build();
    }
}
