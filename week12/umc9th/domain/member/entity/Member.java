package com.example.umc9th.domain.member.entity;


import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.MemberStatus;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.global.auth.enums.Role;
import com.example.umc9th.global.auth.enums.SocialType;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // JPA의 엔티티임 명시
@Builder // @Bulider.Default 이용 시 필요
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 인자 없는 기본 생성자 자동 생성
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 모든 필드를 파라미터로 받는 생성자 자동 생성
@Getter // 모든 필드의 Getter 메서드 자동 생성
@Table(name = "member") // DB의 테이블 정의
// @EntityListeners(AuditingEntityListener.class) // 생성일자, 수정일자 자동 기입 위해 필요
public class Member extends BaseEntity {
    @Id // DB의 PK(Primary Key) 정의
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 생성 전략, IDENETY는 순차적 생성
    private Long id;

    @Column(name = "name", length = 10, nullable = false) // 속성
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING) // 데이터가 ENUM임을 명시
    @Builder.Default // 초깃값 지정
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "point", nullable = false)
    @Builder.Default // 초깃값 지정
    private Integer point = 0;

    @Column(name = "social_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<MemberMission> userMissions = new ArrayList<>();
}