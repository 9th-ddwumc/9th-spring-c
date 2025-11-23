package com.example.umc9th2.domain.member.repository;

import com.example.umc9th2.domain.member.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<MemberMission, Long> {
    @Query("SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE mm.member.id = :memberId " +
            "ORDER BY mm.isComplete ASC, m.deadline ASC, mm.id ASC")
    Page<MemberMission> findUserMissionsByMemberIdSorted(
            @Param("memberId") Long memberId,
            Pageable pageable
    );
}
