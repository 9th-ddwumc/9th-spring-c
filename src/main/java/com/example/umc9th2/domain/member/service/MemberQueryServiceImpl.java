package com.example.umc9th2.domain.member.service.query;
import com.example.umc9th2.domain.member.converter.MemberConverter;

import com.example.umc9th2.domain.member.dto.MemberReqDTO;
import com.example.umc9th2.domain.member.dto.MemberResDTO;
import com.example.umc9th2.domain.member.entity.Member;
import com.example.umc9th2.domain.member.exception.MemberException;
import com.example.umc9th2.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th2.domain.member.repository.MemberRepository;
import com.example.umc9th2.global.auth.enums.CustomUserDetails;
import com.example.umc9th2.global.auth.jwt.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements com.example.umc9th2.domain.member.service.MemberQueryService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public MemberResDTO.LoginDTO login(
            MemberReqDTO.@Valid LoginDTO dto
    ) {

        // Member 조회
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 비밀번호 검증
        if (!encoder.matches(dto.password(), member.getPassword())){
            throw new MemberException(MemberErrorCode.INVALID);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return MemberConverter.toLoginResultDTO(member, accessToken);
    }
}