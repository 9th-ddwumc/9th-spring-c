package com.example.umc9th2.global.auth.enums;

import com.example.umc9th2.domain.member.entity.Member;
import com.example.umc9th2.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th2.domain.member.repository.MemberRepository;
import com.example.umc9th2.global.apiPayload.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // 검증할 Member 조회
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        // CustomUserDetails 반환
        return new CustomUserDetails(member);
    }
}
