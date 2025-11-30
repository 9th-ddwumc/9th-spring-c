package com.example.umc9th2.service;

import com.example.umc9th2.converter.UserConverter;
import com.example.umc9th2.domain.user.User;
import com.example.umc9th2.dto.UserReqDTO;
import com.example.umc9th2.dto.UserResDTO;
import com.example.umc9th2.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th2.global.apiPayload.exception.GeneralException;
import com.example.umc9th2.global.auth.CustomUserDetails;
import com.example.umc9th2.global.auth.enums.UserRole;
import com.example.umc9th2.global.auth.jwt.JwtUtil;
import com.example.umc9th2.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // JWT 미션에서만 필요 (세션 브랜치에서는 null로 두거나 생성자 제거)
    private final JwtUtil jwtUtil;

    @Transactional
    public UserResDTO.SignUpDTO signUp(UserReqDTO.SignUpDTO dto) {

        if (userRepository.existsByEmail(dto.email())) {
            throw new GeneralException(GeneralErrorCode.BAD_REQUEST);
        }

        String encoded = passwordEncoder.encode(dto.password());

        User user = UserConverter.toUser(dto, encoded, UserRole.ROLE_USER);
        User saved = userRepository.save(user);

        return UserConverter.toSignUpDTO(saved);
    }

    // JWT 로그인용
    public UserResDTO.LoginDTO login(UserReqDTO.LoginDTO dto) {

        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        if (!passwordEncoder.matches(dto.password(), user.getPasswordHash())) {
            throw new GeneralException(GeneralErrorCode.UNAUTHORIZED);
        }

        CustomUserDetails userDetails = new CustomUserDetails(user);
        String accessToken = jwtUtil.createAccessToken(userDetails);

        return UserConverter.toLoginDTO(user, accessToken);
    }
}
