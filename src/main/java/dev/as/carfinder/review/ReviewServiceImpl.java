package dev.as.carfinder.review;

import dev.as.carfinder.car.Car;
import dev.as.carfinder.car.CarRepository;
import dev.as.carfinder.user.User;
import dev.as.carfinder.user.UserRepository;
import dev.as.carfinder.review.CreateReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    @Override
    public CreateReviewDTO createReview(Long carId, CreateReviewDTO dto) {
        try {
            Car car = carRepository.findById(carId)
                    .orElseThrow(() -> new RuntimeException("Car not found with id: " + carId));

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = auth.getName();
            User owner = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

            // Create and populate Review entity
            Review review = new Review();
            review.setComment(dto.getComment());
            review.setStars(dto.getStars());
            review.setRating(dto.getRating());
            review.setCar(car);
            review.setOwner(owner);

            // Save and return mapped DTO
            var savedReview = reviewRepository.save(review);
            return new CreateReviewDTO(
                    savedReview.getStars(),
                    savedReview.getComment(),
                    savedReview.getRating()
            );

        } catch (Exception e) {
            e.printStackTrace(); // Always useful for debug
            throw new RuntimeException("Failed to create review: " + e.getMessage());
        }
    }

    @Override
    public List<ReviewDTO> getReviewsByCar(Long carId) {
        return reviewRepository.findByCarId(carId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public ReviewDTO updateReview(Long reviewId, ReviewDTO dto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + reviewId));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User loggedInUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

        if (!review.getOwner().getId().equals(loggedInUser.getId())) {
            throw new SecurityException("You can only edit your own reviews.");
        }

        if (dto.getComment() != null) review.setComment(dto.getComment());
        if (dto.getStars() != null) review.setStars(dto.getStars());
        if (dto.getRating() != null) review.setRating(dto.getRating());

        reviewRepository.save(review);
        return mapToDTO(review);
    }

    // Converts Review entity to DTO
    private ReviewDTO mapToDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setStars(review.getStars());
        dto.setComment(review.getComment());
        if (review.getRating() != null) {
            dto.setRating(Double.valueOf(review.getRating()));
        } else {
            dto.setRating(null); // Or 0.0 if you prefer a default
        }
        dto.setRating(Double.valueOf(review.getRating()));
        dto.setCarId(review.getCar().getId());

        //dto.setComment(String.valueOf(review.getComment()));
        if (review.getOwner() != null) {
            dto.setOwnerId(review.getOwner().getId()); // Critical: set ownerId here
        }

        return dto;
    }
}
