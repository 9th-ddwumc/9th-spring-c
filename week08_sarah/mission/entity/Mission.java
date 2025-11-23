package com.example.umc9th2.domain.mission.entity;

import com.example.umc9th2.domain.member.entity.mapping.MemberMission;
import com.example.umc9th2.domain.store.entity.Store;
import com.example.umc9th2.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "mission_id")
    private Long id;

    @Column (name = "deadline")
    private LocalDate deadline;

    @Column (name = "conditional")
    private String conditional;

    @Column (name = "point")
    private Integer point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "mission")
    private List<MemberMission> memberMissions = new ArrayList<>();
}
