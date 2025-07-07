package dev.as.carfinder.config;

import dev.as.carfinder.car.CarRepository;
import dev.as.carfinder.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarSecurity {

    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public boolean isOwner(Long carId) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepository.findByEmail(email).orElse(null);
        var car = carRepository.findById(carId).orElse(null);

        return car != null && user != null && car.getOwner().getId().equals(user.getId());
    }
}

