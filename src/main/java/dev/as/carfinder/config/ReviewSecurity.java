package dev.as.carfinder.config;

import dev.as.carfinder.review.ReviewRepository;
import dev.as.carfinder.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewSecurity {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public boolean isOwnerOfReview(Long reviewId) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepository.findByEmail(email).orElse(null);

        if (user == null) return false;

        var review = reviewRepository.findById(reviewId).orElse(null);
        return review != null && review.getOwner().getId().equals(user.getId());
    }
}
