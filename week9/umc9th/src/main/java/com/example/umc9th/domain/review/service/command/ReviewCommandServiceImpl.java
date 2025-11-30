package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.ReviewException;
import com.example.umc9th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    // 리뷰 작성
    @Override
    @Transactional
    public ReviewResDTO.CreateDTO writeReview(
            ReviewReqDTO.CreateDTO dto
    ){
        // 1. 가게 조회
        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.STORE_NOT_FOUND));

        // 2. 회원 조회
        Member member = memberRepository.findById(1L)
                .orElseThrow(()-> new ReviewException(ReviewErrorCode.STORE_NOT_FOUND));

        // 3. DTO -> 엔티티 변환
        Review review = ReviewConverter.toReview(dto, member, store);

        // 4. 저장
        Review savedReview = reviewRepository.createReview(review);

        // 5. Entity -> 응답 DTO 변환
        return ReviewConverter.toCreateDTO(savedReview);

    }
}