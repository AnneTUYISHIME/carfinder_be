package dev.as.carfinder.config;

import dev.as.carfinder.bodytype.BodyTypeRepository;
import dev.as.carfinder.car.CarRepository;
import dev.as.carfinder.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandSecurity {

    private final CarRepository carRepository;
    private final BodyTypeRepository bodyTypeRepository;
    private final UserRepository userRepository;

    public boolean isOwnerOfBrand(Long brandId) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("Checking ownership for user: " + email + " and brandId: " + brandId);

        var user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return false;

        return carRepository.findByOwner(user)
                .stream()
                .anyMatch(car -> car.getBrand() != null && car.getBrand().getId().equals(brandId));
    }
}
