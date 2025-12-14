package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.enums.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // id가 memberId이고 status가 ACTIVE인 회원 조회
    Optional<Member> findByIdAndStatus(Long id, MemberStatus status);
    Optional<Member> findByEmail(String email);
}