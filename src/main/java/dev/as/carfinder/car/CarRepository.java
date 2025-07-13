package dev.as.carfinder.car;

import dev.as.carfinder.bodytype.BodyType;
import dev.as.carfinder.brand.Brand;
import dev.as.carfinder.user.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {
   // @ManyToOne
   // @JoinColumn(name = "owner_id")
   // User owner = null;
    List<Car> findByBrand(Brand brand);
    List<Car> findByBodyType(BodyType bodyType);
    List<Car> findByOwner(User owner);

}
