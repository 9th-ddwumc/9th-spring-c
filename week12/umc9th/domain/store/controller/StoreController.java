package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.converter.StoreConverter;
import com.example.umc9th.domain.store.dto.req.StoreRequestDTO;
import com.example.umc9th.domain.store.dto.res.StoreResponseDTO;
import com.example.umc9th.domain.store.service.command.StoreCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;


@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreCommandService storeCommandService;

    @PostMapping(value = "/{storeId}/reviews", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(
            @RequestBody @Valid StoreRequestDTO.ReviewDTO request,
            @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "memberId") Long memberId,
            @RequestPart("reviewPicture")MultipartFile reviewPicture
            ){
        Review review = storeCommandService.createReview(memberId, storeId, request, reviewPicture);
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK, StoreConverter.toCreateReviewResultDTO(review));
    }
}
