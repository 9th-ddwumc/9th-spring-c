package com.example.umc9th.domain.member.dto.res;

import com.example.umc9th.global.auth.enums.Role;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createdAt
    ){}

    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ){}
}
