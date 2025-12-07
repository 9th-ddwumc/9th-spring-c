package com.example.umc9th2.domain.member.dto;

import com.example.umc9th2.global.auth.enums.Role;
import lombok.Builder;

import java.time.LocalDate;

public class MemberResDTO {

    public record JoinDTO(
            Long memberId,
            String name,
            String email,
            LocalDate birth,
            String address,
            String detailAddress
    ) {}

    @Builder
    public record LoginDTO(
            Long memberId,
            String email,
            String name,
            String role,
            String accessToken
    ){}
}
