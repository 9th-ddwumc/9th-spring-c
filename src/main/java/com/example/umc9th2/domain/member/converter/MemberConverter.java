package com.example.umc9th2.domain.member.converter;

import com.example.umc9th2.domain.member.dto.MemberReqDTO;
import com.example.umc9th2.domain.member.dto.MemberResDTO;
import com.example.umc9th2.domain.member.entity.Member;
import com.example.umc9th2.domain.member.enums.SocialType;
import com.example.umc9th2.global.auth.enums.Role;

public class MemberConverter {
    public static Member toMember(
            MemberReqDTO.JoinDTO dto,
            String password,
            Role role
    ) {
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .birth(dto.birth())
                .address(dto.address())
                .detailAddress(dto.specAddress())
                .gender(dto.gender())

                .socialUid(dto.email())
                .socialType(SocialType.KAKAO)
                .point(0)

                .build();
    }
    public static MemberResDTO.LoginDTO toLoginResultDTO(Member member, String accessToken) {
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }
    public static MemberResDTO.JoinDTO toJoinResultDTO(Member member) {
        return new MemberResDTO.JoinDTO(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getBirth(),
                member.getAddress().name(),
                member.getDetailAddress()
        );
    }
}
