package dev.as.carfinder.review;

import dev.as.carfinder.car.Car;
import dev.as.carfinder.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCar(Car car);
    List<Review> findByOwner(User owner);
    List<Review> findByCarId(Long carId);

}

