package dev.as.carfinder.BodyType;

import dev.as.carfinder.BodyType.BodyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BodyTypeRepository extends JpaRepository<BodyType, Long> {
    Optional<BodyType> findByName(String name);
}
