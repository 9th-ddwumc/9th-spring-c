package com.example.umc9th.domain.member.entity.mapping;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.Term;
import jakarta.persistence.*;
import lombok.*;

@Entity // JPA의 엔티티임 명시
@Builder // @Bulider.Default 이용 시 필요
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 인자 없는 기본 생성자 자동 생성
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 모든 필드를 파라미터로 받는 생성자 자동 생성
@Getter // 모든 필드의 Getter 메서드 자동 생성
@Table(name = "member_term") // DB의 테이블 정의
public class MemberTerm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 여러 MemberTerm이 하나의 Member에 속함 (MemberTerm이 N)
    @JoinColumn(name = "member_id") // FK의 주인 설정
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY) // 여러 MemberTerm이 하나의 Term에 속함 (MemberTerm이 N)
    @JoinColumn(name = "term_id")
    private Term term;
}