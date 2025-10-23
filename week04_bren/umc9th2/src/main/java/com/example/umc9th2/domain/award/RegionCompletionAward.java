package com.example.umc9th2.domain.award;

import com.example.umc9th2.domain.common.BaseEntity;
import com.example.umc9th2.domain.mission.Mission;
import com.example.umc9th2.domain.region.Region;
import com.example.umc9th2.domain.user.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "region_completion_awards",
        indexes = {
                @Index(name = "idx_rca_user", columnList = "user_id"),
                @Index(name = "idx_rca_region", columnList = "region_id")
        })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegionCompletionAward extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer points;

    @Column(name = "awarded_at", nullable = false)
    private LocalDateTime awardedAt;

    // 어떤 미션을 달성하다가 지급되었는지(선택적)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    public RegionCompletionAward(Integer points, LocalDateTime awardedAt, User user, Region region, Mission mission) {
        this.points = points;
        this.awardedAt = awardedAt;
        this.user = user;
        this.region = region;
        this.mission = mission;
    }

    public RegionCompletionAward(Integer points, LocalDateTime awardedAt, User user, Region region) {
        this(points, awardedAt, user, region, null);
    }
}
