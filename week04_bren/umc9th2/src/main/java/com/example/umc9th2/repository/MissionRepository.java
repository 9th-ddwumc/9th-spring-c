package com.example.umc9th2.repository;

import com.example.umc9th2.dto.HomeMissionCardDto;
import com.example.umc9th2.domain.mission.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
      select new com.example.umc9th2.dto.HomeMissionCardDto(
        m.id, m.title, s.name, r.id, m.createdAt
      )
      from Mission m
      join m.store s
      join s.region r
      where r.id = :regionId
        and m.active = true
      order by m.sortOrder desc, m.id desc
    """)
    Page<HomeMissionCardDto> findOpenByRegion(
            @Param("regionId") Long regionId,
            Pageable pageable
    );

    @Query("""
      select m from Mission m
      join fetch m.store s
      where s.id = :storeId
      order by m.sortOrder desc, m.id desc
    """)
    List<Mission> findAllByStoreIdWithStore(@Param("storeId") Long storeId);
}
