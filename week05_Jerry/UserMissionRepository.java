package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    @Query("SELECT um FROM UserMission um " +
            "JOIN um.mission m " +
            "JOIN m.store s " +
            "WHERE um.member.id = :memberId " +
            "ORDER BY um.isComplete ASC, m.deadline ASC, um.id ASC")
    Page<UserMission> findUserMissionsByMemberIdSorted(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

}
