package com.example.umc9th2.controller;

import com.example.umc9th2.dto.MyMissionCardDto;
import com.example.umc9th2.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    // 내 미션(진행중/완료)
    @GetMapping("/{userId}/missions")
    public Page<MyMissionCardDto> getMyMissions(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return myPageService.getMyMissions(userId, PageRequest.of(page, size));
    }

    // 내 배지
    @GetMapping("/{userId}/awards")
    public Object getMyAwards(@PathVariable Long userId) {
        return myPageService.getMyAwards(userId);
    }
}
