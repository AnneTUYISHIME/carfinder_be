package dev.as.carfinder.review;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @SecurityRequirement(name = "auth")
    @PostMapping
    public ReviewDTO createReview(@RequestBody ReviewDTO dto) {
        return reviewService.createReview(dto);
    }

    @SecurityRequirement(name = "auth")
    @GetMapping("/{id}")
    public ReviewDTO getReview(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @SecurityRequirement(name = "auth")
    @PreAuthorize("@reviewSecurity.isOwnerOfReview(#id)")
    @PutMapping("/{id}")
    public ReviewDTO updateReview(@PathVariable Long id, @RequestBody ReviewDTO dto) {
        return reviewService.updateReview(id, dto);
    }

    @SecurityRequirement(name = "auth")
    @PreAuthorize("@reviewSecurity.isOwnerOfReview(#id)")
    @PatchMapping("/{id}")
    public ReviewDTO patchReview(@PathVariable Long id, @RequestBody ReviewDTO dto) {
        return reviewService.patchReview(id, dto);
    }

    @SecurityRequirement(name = "auth")
    @PreAuthorize("@reviewSecurity.isOwnerOfReview(#id)")
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}