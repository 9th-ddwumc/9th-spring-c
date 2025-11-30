package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.enums.MemberStatus;
import com.example.umc9th.global.auth.enums.Role;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberConverter {

    // Entity -> DTO
    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public Member toMember(
            MemberReqDTO.JoinDTO dto,
            String password,
            Role role
    ) {
        return Member.builder()
                .name(dto.name())
                .email(dto.email()) // 추가된 코드
                .password(password) // 추가된 코드
                .role(role)         // 추가된 코드

                .birth(dto.birth())
                .address(dto.address())
                .gender(dto.gender())
                .phoneNumber(dto.phoneNumber())

                .socialType(dto.socialType())
                .status(MemberStatus.ACTIVE)

                .build();
    }

    public static MemberResDTO.LoginDTO toLoginDTO(
            Member member,
            String accessToken
    ){
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }


}
