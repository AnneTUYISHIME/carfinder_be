package dev.as.carfinder.review;

import java.util.List;

public interface ReviewService {
    ReviewDTO createReview(ReviewDTO dto);
    ReviewDTO getReviewById(Long id);
    List<ReviewDTO> getAllReviews();
    ReviewDTO updateReview(Long id, ReviewDTO dto);
    ReviewDTO patchReview(Long id, ReviewDTO dto);
    void deleteReview(Long id);
}
