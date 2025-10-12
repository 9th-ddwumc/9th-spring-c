package com.example.umc9th2.domain.mission;

import com.example.umc9th2.domain.common.BaseEntity;
import com.example.umc9th2.domain.user.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user_mission_completions",
        indexes = {
                @Index(name = "idx_umc_user", columnList = "user_id"),
                @Index(name = "idx_umc_mission", columnList = "mission_id"),
                @Index(name = "idx_umc_user_mission", columnList = "user_id, mission_id", unique = true)
        })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMissionCompletion extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "completed_at", nullable = false)
    private LocalDateTime completedAt;

    public UserMissionCompletion(Mission mission, User user, LocalDateTime completedAt) {
        this.mission = mission;
        this.user = user;
        this.completedAt = completedAt;
    }
}
