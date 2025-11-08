package com.example.umc9th2.repository;

import com.example.umc9th2.domain.award.RegionCompletionAward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionCompletionAwardRepository extends JpaRepository<RegionCompletionAward, Long> {

    @Query("""
      select rca
      from RegionCompletionAward rca
      join fetch rca.region r
      where rca.user.id = :userId
      order by rca.createdAt desc
    """)
    List<RegionCompletionAward> findAwardsByUser(@Param("userId") Long userId);
}
