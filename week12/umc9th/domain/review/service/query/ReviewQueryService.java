package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.QLocation;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;

    public List<ReviewResponse> searchReview(String query, String type) {
        // Q 클래스 정의
        QReview review = QReview.review;
        QLocation location = QLocation.location;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // BooleanBuilder 사용

        // 동적 쿼리: 검색 조건
        if (type.equals("location")){
            builder.and(location.name.contains(query));
        }
        if (type.equals("star")){
            double base = Double.parseDouble(query);
            builder.and(review.star.goe(base).and(review.star.lt(base + 1.0)));
        }
        if (type.equals("both")){
            // & 기준 변환
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            // 동적 쿼리
            builder.and(location.name.contains(firstQuery));
            builder.and(review.star.goe(Double.parseDouble(secondQuery)));
        }

        // Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);

        // 리턴
        return reviewList.stream()
                .map(r -> new ReviewResponse(
                                r.getId(),
                                r.getContent(),
                                r.getStar(),
                                r.getStore().getName(),
                                r.getStore().getLocation().getName()
                        )
                )
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> searchReviewByStoreAndRating(String storeName, Integer ratingBucket) {
        return reviewRepository.searchReviewByStoreAndRating(storeName, ratingBucket);
    }

    public ReviewResDTO.ReviewMyPreviewListDTO findMyReview(Long memberId, Integer page){
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<Review> result =
                reviewRepository.findAllByMemberId(memberId, pageRequest);

        return ReviewConverter.toReviewMyPreviewListDTO(result);
    }
}