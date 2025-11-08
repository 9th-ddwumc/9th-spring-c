package com.example.umc9th2.service;

import com.example.umc9th2.dto.HomeMissionCardDto;
import com.example.umc9th2.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public Page<HomeMissionCardDto> getOpenMissionsByRegion(Long regionId, Pageable pageable) {
        return missionRepository.findOpenByRegion(regionId, pageable);
    }
}
