package com.example.umc9th2.domain.mission.repository;

import com.example.umc9th2.domain.mission.entity.MissionChallenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionChallengeRepository extends JpaRepository<MissionChallenge, Long> {
    Page<MissionChallenge> findByMemberIdAndStatus(Long memberId, String status, Pageable pageable);
}