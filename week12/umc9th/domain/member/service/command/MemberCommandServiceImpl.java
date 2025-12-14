package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.auth.enums.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto) {

        // 1. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(dto.password());

        // 2. DTO -> Entity (암호화된 비밀번호 + ROLE_USER 사용)
        Member member = MemberConverter.toMember(dto, encodedPassword, Role.ROLE_USER);

        // 3. DB 저장
        Member saved = memberRepository.save(member);

        // 4. 응답 DTO 생성
        return MemberConverter.toJoinDTO(saved);
    }
}
