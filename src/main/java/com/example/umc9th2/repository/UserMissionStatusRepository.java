package com.example.umc9th2.repository;

import com.example.umc9th2.domain.mission.UserMissionStatus;
import com.example.umc9th2.domain.mission.Mission;
import com.example.umc9th2.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionStatusRepository extends JpaRepository<UserMissionStatus, Long> {

    boolean existsByUserAndMission(User user, Mission mission);
}
