package com.example.umc9th2.domain.mission;

import com.example.umc9th2.domain.common.BaseEntity;
import com.example.umc9th2.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder   // ★ 이거 추가
public class UserMissionStatus extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // ★ PK 이름이 다르면 여기도 바꾸고, 아래 getId()도 맞춰야 함

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Mission mission;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;
}
