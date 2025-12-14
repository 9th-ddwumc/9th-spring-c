package com.example.umc9th.domain.member.service.query;

import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;

public interface MemberQueryService {
    MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO dto);
}
