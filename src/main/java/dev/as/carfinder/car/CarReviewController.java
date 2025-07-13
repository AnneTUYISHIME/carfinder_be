package dev.as.carfinder.car;

import dev.as.carfinder.review.CreateReviewDTO;
import dev.as.carfinder.review.ReviewDTO;
import dev.as.carfinder.review.ReviewService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
//@SecurityRequirement(name = "auth")
public class CarReviewController {

    private final ReviewService reviewService;

    // POST: Add a review to a specific car
    @SecurityRequirement(name = "auth")
    @PostMapping("/{carId}/reviews")
    public ResponseEntity<CreateReviewDTO> createReview(@PathVariable Long carId, @RequestBody CreateReviewDTO dto) {
        CreateReviewDTO savedReview =  reviewService.createReview(carId, dto);
        return ResponseEntity.ok(savedReview);
    }


    /* GET: Get a single review by its ID
    @GetMapping("/reviews/{reviewId}")
    public ReviewDTO getReview(@PathVariable("reviewId") Long reviewId) {
        return reviewService.getReviewById(reviewId);
    }*/

    // GET: Get all reviews for a specific car
    @GetMapping("/{carId}/reviews")
    public List<ReviewDTO> getReviewsForCar(@PathVariable Long carId) {
        return reviewService.getReviewsByCar(carId);
    }
    @PreAuthorize("@reviewSecurity.isOwner(#id)")
    @SecurityRequirement(name = "auth")
    @PutMapping("/reviews/{reviewId}")
    public ReviewDTO updateReview(@PathVariable Long reviewId, @RequestBody ReviewDTO dto) {
        return reviewService.updateReview(reviewId, dto);
    }

}
