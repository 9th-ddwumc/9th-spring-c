package com.example.umc9th2.controller;

import com.example.umc9th2.dto.ReviewSearchRequest;
import com.example.umc9th2.dto.ReviewSummaryDto;
import com.example.umc9th2.service.ReviewQueryService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewQueryController {

    private final ReviewQueryService service;

    public ReviewQueryController(ReviewQueryService service) {
        this.service = service;
    }

    @GetMapping("/me")
    public Page<ReviewSummaryDto> searchMyReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) String store,
            @RequestParam(required = false) Integer starBand,
            @RequestParam(defaultValue = "recent") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        ReviewSearchRequest req = new ReviewSearchRequest();
        req.setUserId(userId);
        req.setStore(store);
        req.setStarBand(starBand);
        req.setSort(sort);

        return service.searchMyReviews(req, page, size);
    }
}
