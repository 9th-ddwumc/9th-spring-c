package com.example.umc9th2.repository;

import com.example.umc9th2.domain.mission.MissionStatus;
import com.example.umc9th2.domain.mission.UserMissionStatus;
import com.example.umc9th2.domain.mission.Mission;
import com.example.umc9th2.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionStatusRepository extends JpaRepository<UserMissionStatus, Long> {

    boolean existsByUserAndMission(User user, Mission mission);

    // chapter 9. 3번 API: 특정 유저의 "진행중(IN_PROGRESS)" 미션 목록 조회
    Page<UserMissionStatus> findByUserIdAndStatus(
            Long userId,
            MissionStatus status,
            Pageable pageable
    );
}
