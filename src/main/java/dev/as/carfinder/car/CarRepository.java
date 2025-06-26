package dev.as.carfinder.car;

import dev.as.carfinder.bodytype.BodyType;
import dev.as.carfinder.brand.Brand;
import dev.as.carfinder.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findBySeller(User seller);
    List<Car> findByBrand(Brand brand);
    List<Car> findByBodyType(BodyType bodyType);
}
