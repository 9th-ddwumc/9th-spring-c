package com.example.umc9th.domain.mission.entity;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity // JPA의 엔티티임 명시
@Builder // @Bulider.Default 이용 시 필요
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 인자 없는 기본 생성자 자동 생성
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 모든 필드를 파라미터로 받는 생성자 자동 생성
@Getter // 모든 필드의 Getter 메서드 자동 생성
@Table(name = "mission") // DB의 테이블 정의

public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "cost", nullable = false)
    private Integer cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<MemberMission> memberMissions = new ArrayList<>();
}