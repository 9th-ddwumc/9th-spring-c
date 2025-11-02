package com.example.umc9th2.controller;

import com.example.umc9th2.dto.HomeMissionCardDto;
import com.example.umc9th2.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // GET /api/missions/region/1?page=0&size=10
    @GetMapping("/region/{regionId}")
    public Page<HomeMissionCardDto> getMissionsByRegion(
            @PathVariable Long regionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return missionService.getOpenMissionsByRegion(regionId, PageRequest.of(page, size));
    }
}
