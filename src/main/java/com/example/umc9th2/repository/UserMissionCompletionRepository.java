package com.example.umc9th2.repository;

import com.example.umc9th2.domain.mission.UserMissionCompletion;
import com.example.umc9th2.dto.MyMissionCardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionCompletionRepository extends JpaRepository<UserMissionCompletion, Long> {

    @Query("""
      select new com.example.umc9th2.dto.MyMissionCardDto(
        umc.id, m.id, s.name, m.title, umc.createdAt
      )
      from UserMissionCompletion umc
      join umc.mission m
      join m.store s
      where umc.user.id = :userId
      order by umc.createdAt desc
    """)
    Page<MyMissionCardDto> findMyMissionCards(
            @Param("userId") Long userId,
            Pageable pageable
    );
}
