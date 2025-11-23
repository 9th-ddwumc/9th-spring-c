package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Page<MemberMission> findAllByMemberIdAndIsCompleteFalse(Long memberId, Pageable pageable);

    @Query("SELECT mm FROM MemberMission mm " +
            "JOIN mm.mission m " +
            "JOIN m.store s " +
            "WHERE mm.member.id = :memberId " +
            "ORDER BY mm.isComplete ASC, m.deadline ASC, mm.id ASC")
    Page<MemberMission> findUserMissionsByMemberIdSorted(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    boolean existsByMemberAndMission(Member member, Mission mission);

}