package com.example.umc9th.domain.member.dto.req;

import com.example.umc9th.domain.member.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.example.umc9th.global.auth.enums.SocialType;
import java.time.LocalDate;

public class MemberReqDTO {

    public record JoinDTO(
            @NotBlank
            String name,
            @Email
            String email, // 추가된 속성
            @NotBlank
            String password, // 추가된 속성
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
            String address,
            @NotNull
            SocialType socialType,

            String phoneNumber

    ){}

    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}