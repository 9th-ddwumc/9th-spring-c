package com.example.umc9th.domain.store.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.entity.ReviewPhoto;
import com.example.umc9th.domain.review.repository.ReviewPhotoRepository;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.converter.StoreConverter;
import com.example.umc9th.domain.store.dto.req.StoreRequestDTO;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.domain.uuid.entity.Uuid;
import com.example.umc9th.domain.uuid.repository.UuidRepository;
import com.example.umc9th.global.aws.s3.AmazonS3Manager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final AmazonS3Manager s3Manager;
    private final UuidRepository uuidRepository;
    private final ReviewPhotoRepository reviewImageRepository;

    public Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDTO request, MultipartFile reviewPicture) {

        Member member = memberRepository.findById(memberId).orElseThrow();
        Store store = storeRepository.findById(storeId).orElseThrow();

        Review review = StoreConverter.toReview(request);
        review.setMember(member);
        review.setStore(store);

        Review savedReview = reviewRepository.save(review);

        if (reviewPicture != null && !reviewPicture.isEmpty()) {
            String uuid = UUID.randomUUID().toString();
            Uuid savedUuid = uuidRepository.save(
                    Uuid.builder()
                            .uuid(uuid)
                            .build()
            );

            String pictureUrl = s3Manager.uploadFile(
                    s3Manager.generateReviewKeyName(savedUuid),
                    reviewPicture
            );

            ReviewPhoto reviewPhoto = ReviewConverter.toReviewPhoto(pictureUrl, savedReview);
            reviewImageRepository.save(reviewPhoto);
        }

        return savedReview;
    }
}
