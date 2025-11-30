package com.example.umc9th2.domain.member.service;
import com.example.umc9th2.domain.member.converter.MemberConverter;
import com.example.umc9th2.domain.member.dto.MemberReqDTO;
import com.example.umc9th2.domain.member.dto.MemberResDTO;
import com.example.umc9th2.domain.member.entity.Member;
import com.example.umc9th2.domain.member.repository.MemberRepository;
import com.example.umc9th2.domain.member.service.MemberCommandService;
import com.example.umc9th2.global.auth.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto) {

        // 솔트된 비밀번호 생성
        String salt = passwordEncoder.encode(dto.password());

        // 사용자 생성
        Member member = MemberConverter.toMember(dto, salt, Role.ROLE_USER);

        // DB 저장
        memberRepository.save(member);

        // 반환 DTO 생성
        return MemberConverter.toJoinResultDTO(member);
    }
}
