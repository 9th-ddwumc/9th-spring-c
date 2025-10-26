package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.MissionTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface MissionTemplateRepository extends JpaRepository<MissionTemplate, Long> {

    @Query(
            "SELECT mt FROM MissionTemplate mt " +
                    "JOIN mt.shop s " +
                    "WHERE s.region.id = :regionId " +
                    "AND mt.id NOT IN ( " +
                    "SELECT m.missionTemplateId FROM Mission m " +
                    "WHERE m.member.id = :memberId " +
                    "AND m.status IN ('ing', 'end') " +
                    ") " +
                    "ORDER BY mt.id DESC"
    )
    List<MissionTemplate> findAvailableMissions(@Param("regionId") Long regionId,
                                                @Param("memberId") Long memberId,
                                                Pageable pageable);
}
