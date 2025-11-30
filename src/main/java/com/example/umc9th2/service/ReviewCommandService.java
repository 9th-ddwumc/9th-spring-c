package com.example.umc9th2.service;

import com.example.umc9th2.domain.review.Review;
import com.example.umc9th2.domain.store.Store;
import com.example.umc9th2.domain.user.User;
import com.example.umc9th2.dto.ReviewCreateRequest;
import com.example.umc9th2.repository.ReviewRepository;
import com.example.umc9th2.repository.StoreRepository;
import com.example.umc9th2.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    // 하드코딩 유저
    private static final Long FIXED_USER_ID = 1L;

    @Transactional
    public Long createReview(ReviewCreateRequest request) {

        // @ExistStore로 1차 검증하지만, 혹시 몰라서 한 번 더 안전하게 조회
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다."));

        User user = userRepository.findById(FIXED_USER_ID)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Review review = Review.builder()
                .store(store)
                .user(user)
                .content(request.getContent())
                .star(request.getStar())
                .build();

        return reviewRepository.save(review).getId();
    }
}
