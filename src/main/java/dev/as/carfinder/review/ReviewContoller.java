package dev.as.carfinder.review;

import dev.as.carfinder.review.ReviewDTO;
import dev.as.carfinder.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewContoller {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ReviewDTO createReview(@RequestBody ReviewDTO dto) {
        return reviewService.createReview(dto);
    }

    @GetMapping("/{id}")
    public ReviewDTO getReview(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @PutMapping("/{id}")
    public ReviewDTO updateReview(@PathVariable Long id, @RequestBody ReviewDTO dto) {
        return reviewService.updateReview(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}