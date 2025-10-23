package com.example.umc9th2.repository;

import com.example.umc9th2.domain.mission.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {}
