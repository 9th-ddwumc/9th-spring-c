package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    Page<Mission> findAllByStoreId(Long storeId, Pageable pageable);

    @Query("SELECT m FROM Mission m " +
            "JOIN m.store s " +
            "JOIN s.location l " +
            "JOIN MemberMission mm " +
            "ON mm.mission = m " +
            "AND mm.member.id = :memberId " +
            "WHERE l.name = :locationName " +
            "AND m.deadline >= :currentDate " +
            "AND mm.isComplete = false " +
            "ORDER BY m.deadline ASC, m.id ASC "
    )

    Page<Mission> findAvailableMissionsByLocationAndMember(
            @Param("memberId") Long memberId,
            @Param("locationName") String locationName,
            @Param("currentDate") LocalDate currentDate,
            Pageable pageable
    );
}