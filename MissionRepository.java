package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 진행 중 미션 조회
    List<Mission> findByMemberIdAndStatusOrderByCreateAtDescIdDesc(Long memberId, String status, Pageable pageable);

    // 완료된 미션 조회 (리뷰 포함)
    @Query(
            "SELECT m FROM Mission m " +
                    "LEFT JOIN Review r ON r.member.id = m.member.id AND r.missionTemplateId = m.missionTemplateId " +
                    "WHERE m.member.id = :memberId " +
                    "AND m.status = 'end' " +
                    "ORDER BY m.completedAt DESC, m.id DESC"
    )
    List<Mission> findCompletedMissions(@Param("memberId") Long memberId, Pageable pageable);
}
