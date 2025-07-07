package dev.as.carfinder.config;

import dev.as.carfinder.bodytype.BodyTypeRepository;
import dev.as.carfinder.review.ReviewRepository;
import dev.as.carfinder.car.CarRepository;
import dev.as.carfinder.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class ReviewSecurity {

    private final CarRepository carRepository;
    private final BodyTypeRepository bodyTypeRepository;
    private final UserRepository userRepository;

    public boolean isOwnerOfReview(Long reviewId) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();

        System.out.println("Checking ownership for user: " + email + " and reviewId: " + reviewId);

        var user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return false;

        return carRepository.findByOwner(user)
                .stream()
                .flatMap(car -> car.getReviews() != null ? car.getReviews().stream() : Stream.empty())
                .anyMatch(review -> review.getId().equals(reviewId));
    }
}
