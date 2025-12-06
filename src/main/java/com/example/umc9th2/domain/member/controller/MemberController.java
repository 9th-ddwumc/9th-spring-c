package com.example.umc9th2.domain.member.controller;

import com.example.umc9th2.domain.member.dto.MemberReqDTO;
import com.example.umc9th2.domain.member.exception.code.MemberSuccessCode;
import com.example.umc9th2.domain.member.service.MemberCommandService;
import com.example.umc9th2.domain.member.service.MemberQueryService;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.Generated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/sign-up")
    public ApiResponse signUp(@RequestBody MemberReqDTO.@Valid JoinDTO dto) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, memberCommandService.signup(dto));
    }

    {}
    @PostMapping("/login")
    public ApiResponse login(@RequestBody @Valid MemberReqDTO.LoginDTO dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberQueryService.login(dto));
    }
    @Generated
    public MemberController(final MemberCommandService memberCommandService, MemberQueryService memberQueryService) {
        this.memberCommandService = memberCommandService;
        this.memberQueryService = memberQueryService;
    }
}
