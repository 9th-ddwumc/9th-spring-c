package com.example.umc9th.domain.store.entity;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.enums.OpenType;
import com.example.umc9th.domain.store.enums.StoreType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity // JPA의 엔티티임 명시
@Builder // @Bulider.Default 이용 시 필요
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 인자 없는 기본 생성자 자동 생성
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 모든 필드를 파라미터로 받는 생성자 자동 생성
@Getter // 모든 필드의 Getter 메서드 자동 생성
@Table(name = "store") // DB의 테이블 정의
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreType type;

    @Column(name = "open")
    @Enumerated(EnumType.STRING)
    private OpenType open;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "owner_number", nullable = false)
    private BigInteger ownerNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Mission> missions = new ArrayList<>();
}