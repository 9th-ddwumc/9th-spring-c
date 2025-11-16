package com.example.umc9th2.domain.mission.controller;

import com.example.umc9th2.domain.mission.dto.req.MissionChallengeRequestDto;
import com.example.umc9th2.domain.mission.dto.res.MissionChallengeResponseDto;
import com.example.umc9th2.domain.mission.service.MissionChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionChallengeService missionChallengeService;

    @PostMapping("/challenge")
    public MissionChallengeResponseDto challengeMission(@RequestBody MissionChallengeRequestDto requestDto) {
        return missionChallengeService.challengeMission(requestDto);
    }
}
