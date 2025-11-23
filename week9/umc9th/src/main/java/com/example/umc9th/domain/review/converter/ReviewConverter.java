package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

public class ReviewConverter {
    // Entity -> DTO
    public static ReviewResDTO.CreateDTO toCreateDTO(Review review) {
        return ReviewResDTO.CreateDTO.builder()
                .reviewId(review.getId())
                .star(review.getStar())
                .content(review.getContent())
                .storeName(review.getStore().getName())
                .build();
    }

    // DTO -> Entity
    public static Review toReview(
            ReviewReqDTO.CreateDTO dto,
            Member member,
            Store store) {
        return Review.builder()
                .content(dto.content())
                .star(dto.star())
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewResDTO.ReviewMyPreviewListDTO toReviewMyPreviewListDTO(
            Page<Review> result
    ) {
        return ReviewResDTO.ReviewMyPreviewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewMyPreviewDTO)
                        .toList()
                )
                .listSize(result.getNumberOfElements()) // 현재 페이지 개수
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    // Review → 내가 작성한 리뷰 1개 프리뷰 DTO
    public static ReviewResDTO.ReviewMyPreviewDTO toReviewMyPreviewDTO(
            Review review
    ) {
        return ReviewResDTO.ReviewMyPreviewDTO.builder()
                .storeName(review.getStore().getName())  // 내가 쓴 리뷰 → 가게명 필요!!
                .score((float) review.getStar())
                .body(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate()) // 공주님 createdAt 타입에 맞게 LocalDate
                .build();
    }
}