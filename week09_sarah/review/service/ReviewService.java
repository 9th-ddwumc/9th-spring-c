package com.example.umc9th2.domain.review.service;

import com.example.umc9th2.domain.member.entity.Member;
import com.example.umc9th2.domain.member.repository.MemberRepository;
import com.example.umc9th2.domain.review.dto.req.ReviewCreateRequestDto;
import com.example.umc9th2.domain.review.dto.res.ReviewCreateResponseDto;
import com.example.umc9th2.domain.review.dto.res.ReviewResponseDto;
import com.example.umc9th2.domain.review.entity.Review;
import com.example.umc9th2.domain.review.repository.ReviewRepository;
import com.example.umc9th2.domain.store.entity.Store;
import com.example.umc9th2.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    // 로그인 기능 없음 -> 항상 member_id = 1 사용
    private static final Long HARDCODED_MEMBER_ID = 1L;

    @Transactional
    public ReviewCreateResponseDto createReview(ReviewCreateRequestDto requestDto) {
        // 1. 가게 조회
        Store store = storeRepository.findById(requestDto.getStoreId())
                .orElseThrow(() -> new RuntimeException("해당 가게를 찾을 수 없습니다."));

        // 2. 하드코딩 회원 조회
        Member member = memberRepository.findById(HARDCODED_MEMBER_ID)
                .orElseThrow(() -> new RuntimeException("하드코딩된 회원을 찾을 수 없습니다."));

        // 3. Review 엔티티 생성
        Review review = Review.builder()
                .store(store)
                .member(member)
                .star(requestDto.getStar())       // rating -> star
                .content(requestDto.getContent()) // review_text -> content
                .build();

        // 4. 저장
        Review saved = reviewRepository.save(review);

        // 5. 응답 DTO 변환
        return ReviewCreateResponseDto.builder()
                .reviewId(saved.getId())
                .storeId(store.getId())
                .storeName(store.getName())
                .userId(member.getId())
                .memberName(member.getName())
                .star(saved.getStar())
                .content(saved.getContent())
                .photoUrls(null)
                .createdAt(saved.getCreatedAt())
                .build();
    }

    public Page<ReviewResponseDto> getMyReviews(Long memberId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Review> reviews = reviewRepository.findByMemberId(memberId, pageable);

        return reviews.map(r ->
                ReviewResponseDto.builder()
                        .reviewId(r.getId())
                        .storeId(r.getStore().getId())
                        .storeName(r.getStore().getName())
                        .star(r.getStar())
                        .content(r.getContent())
                        .createdAt(r.getCreatedAt())
                        .build()
        );
    }
}
