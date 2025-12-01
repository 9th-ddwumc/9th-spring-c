package com.example.umc9th2.domain.mission.service;

import com.example.umc9th2.domain.member.entity.Member;
import com.example.umc9th2.domain.member.repository.MemberRepository;
import com.example.umc9th2.domain.mission.dto.req.MissionChallengeRequestDto;
import com.example.umc9th2.domain.mission.dto.res.MissionChallengeResponseDto;
import com.example.umc9th2.domain.mission.entity.Mission;
import com.example.umc9th2.domain.mission.entity.MissionChallenge;
import com.example.umc9th2.domain.mission.repository.MissionChallengeRepository;
import com.example.umc9th2.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionChallengeService {

    private final MissionRepository missionRepository;
    private final MissionChallengeRepository missionChallengeRepository;
    private final MemberRepository memberRepository;

    // 로그인 기능 없음, DB에 있는 1번 유저 사용
    private final Long HARDCODED_MEMBER_ID = 1L;

    public MissionChallengeResponseDto challengeMission(MissionChallengeRequestDto requestDto) {

        Mission mission = missionRepository.findById(requestDto.getMissionId())
                .orElseThrow(() -> new RuntimeException("미션을 찾을 수 없습니다."));

        Member member = memberRepository.findById(HARDCODED_MEMBER_ID)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        MissionChallenge challenge = MissionChallenge.builder()
                .mission(mission)
                .member(member)
                .status("in-progress")
                .build();

        MissionChallenge saved = missionChallengeRepository.save(challenge);

        return MissionChallengeResponseDto.builder()
                .missionChallengeId(saved.getId())
                .missionId(mission.getId())
                .memberId(member.getId())
                .status(saved.getStatus())
                .message("미션 도전이 시작되었습니다.")
                .build();
    }
}
