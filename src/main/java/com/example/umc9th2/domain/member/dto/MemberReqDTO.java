package com.example.umc9th2.domain.member.dto;

import com.example.umc9th2.domain.member.enums.Address;
import com.example.umc9th2.domain.member.enums.Gender;
import com.example.umc9th2.global.validator.ExistFoods;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    public record JoinDTO (
        @NotBlank
        String name,
        @Email
        String email,
        @NotBlank
        String password,
        @NotNull
        Gender gender,
        @NotNull
        LocalDate birth,
        @NotNull
        Address address,
        @NotNull
        String specAddress,
        @ExistFoods
        List<Long> preferCategory
    ) {}
    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}
