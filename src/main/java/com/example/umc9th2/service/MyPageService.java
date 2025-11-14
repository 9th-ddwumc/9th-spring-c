package com.example.umc9th2.service;

import com.example.umc9th2.domain.award.RegionCompletionAward;
import com.example.umc9th2.dto.MyMissionCardDto;
import com.example.umc9th2.repository.RegionCompletionAwardRepository;
import com.example.umc9th2.repository.UserMissionCompletionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserMissionCompletionRepository umcRepo;
    private final RegionCompletionAwardRepository awardRepo;

    public Page<MyMissionCardDto> getMyMissions(Long userId, Pageable pageable) {
        return umcRepo.findMyMissionCards(userId, pageable);
    }

    public List<RegionCompletionAward> getMyAwards(Long userId) {
        return awardRepo.findAwardsByUser(userId);
    }
}
