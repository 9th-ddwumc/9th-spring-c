package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.exception.MemberMissionException;
import com.example.umc9th.domain.mission.exception.code.MemberMissionErrorCode;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberMissionService {
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MemberMission challengeMission(Long memberId, Long missionId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberMissionException(MemberMissionErrorCode.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MemberMissionException(MemberMissionErrorCode.MISSION_NOT_FOUND));

        // 이미 도전한 미션인지 체크 (도전 중/완료 모두 포함해서 막기)
        if (memberMissionRepository.existsByMemberAndMission(member, mission)) {
            throw new MemberMissionException(MemberMissionErrorCode.ALREADY_CHALLENGED);
        }

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .isComplete(false)
                .build();

        return memberMissionRepository.save(memberMission);
    }
}