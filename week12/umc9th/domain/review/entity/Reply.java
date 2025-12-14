package com.example.umc9th.domain.review.entity;

import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity // JPA의 엔티티임 명시
@Builder // @Bulider.Default 이용 시 필요
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 인자 없는 기본 생성자 자동 생성
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 모든 필드를 파라미터로 받는 생성자 자동 생성
@Getter // 모든 필드의 Getter 메서드 자동 생성
@Table(name = "reply") // DB의 테이블 정의
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;
}