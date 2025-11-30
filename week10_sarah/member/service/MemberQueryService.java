package com.example.umc9th2.domain.member.service;

import com.example.umc9th2.domain.member.dto.MemberReqDTO;
import com.example.umc9th2.domain.member.dto.MemberResDTO;

public interface MemberQueryService {
    MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO dto);
}
