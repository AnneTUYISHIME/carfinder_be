package dev.as.carfinder.car;

import dev.as.carfinder.car.Car;
import dev.as.carfinder.user.User;
import dev.as.carfinder.brand.Brand;
import dev.as.carfinder.BodyType.BodyType;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findBySeller(User seller);
    List<Car> findByBrand(Brand brand);
    List<Car> findByBodyType(BodyType bodyType);
}
