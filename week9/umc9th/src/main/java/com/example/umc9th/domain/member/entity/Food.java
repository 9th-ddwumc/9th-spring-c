package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.enums.FoodName;
import jakarta.persistence.*;
import lombok.*;

@Entity // JPA의 엔티티임 명시
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 인자 없는 기본 생성자 자동 생성
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 모든 필드를 파라미터로 받는 생성자 자동 생성
@Getter // 모든 필드의 Getter 메서드 자동 생성
@Table(name = "food") // DB의 테이블 정의
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private FoodName name;
}