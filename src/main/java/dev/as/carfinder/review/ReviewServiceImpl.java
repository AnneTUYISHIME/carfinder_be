package dev.as.carfinder.review;

import dev.as.carfinder.car.Car;
import dev.as.carfinder.car.CarRepository;
import dev.as.carfinder.user.User;
import dev.as.carfinder.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ReviewDTO createReview(ReviewDTO dto) {
        Review review = new Review();
        mapDtoToEntity(dto, review);
        reviewRepository.save(review);
        return mapEntityToDto(review);
    }

    @Override
    public ReviewDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        return mapEntityToDto(review);
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO updateReview(Long id, ReviewDTO dto) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        mapDtoToEntity(dto, review);
        reviewRepository.save(review);
        return mapEntityToDto(review);
    }

    @Override
    public ReviewDTO patchReview(Long id, ReviewDTO dto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (dto.getName() != null) review.setName(dto.getName());
        if (dto.getStars() != null) review.setStars(dto.getStars());

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            review.setUser(user);
        }

        if (dto.getCarId() != null) {
            Car car = carRepository.findById(dto.getCarId())
                    .orElseThrow(() -> new RuntimeException("Car not found"));
            review.setCar(car);
        }

        Review saved = reviewRepository.save(review);
        return mapEntityToDto(saved);
    }


    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    private void mapDtoToEntity(ReviewDTO dto, Review review) {
        review.setName(dto.getName());
        review.setStars(dto.getStars());

        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Car car = carRepository.findById(dto.getCarId()).orElseThrow(() -> new RuntimeException("Car not found"));

        review.setUser(user);
        review.setCar(car);
    }

    private ReviewDTO mapEntityToDto(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setName(review.getName());
        dto.setStars(review.getStars());
        dto.setUserId(review.getUser().getId());
        dto.setCarId(review.getCar().getId());
        return dto;
    }
}
