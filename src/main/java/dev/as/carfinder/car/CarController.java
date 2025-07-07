package dev.as.carfinder.car;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
@SecurityRequirement(name = "auth")
public class CarController {

    private final CarService carService;

    //  Create a car

    @GetMapping("/test-role")
    @PreAuthorize("hasRole('USER')")
    public String testUserRoleCar() {
        return "✅ Access granted to USER on car!";
    }

    @PostMapping
    public CarDTO createCar(@RequestBody CarDTO dto) {
        return carService.createCar(dto);
    }

    //  Get a car by id (any authenticated user)
    @GetMapping("/{id}")
    public CarDTO getCar(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    //  Get ALL cars (authenticated)
    @GetMapping
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    //  Get FILTERED cars (authenticated)
    @GetMapping("/filter")
    public List<CarDTO> filterCars(
            @RequestParam(required = false) Long brandId,
            @RequestParam(required = false) Long bodyTypeId,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String driveType
    ) {
        return carService.filterCars(brandId, bodyTypeId, minPrice, maxPrice, location, driveType);
    }

    //  Get cars owned by current logged-in user
    @GetMapping("/mine")
    public List<CarDTO> getMyCars(Authentication authentication) {
        return carService.getCarsByOwnerEmail(authentication.getName());
    }

    //  Update car (only if current user is the owner)
    @PreAuthorize("@carSecurity.isOwner(#id)")
    @PutMapping("/{id}")
    public CarDTO updateCar(@PathVariable Long id, @RequestBody CarDTO dto) {
        return carService.updateCar(id, dto);
    }

    //  Patch car (only if current user is the owner)
    @PreAuthorize("@carSecurity.isOwner(#id)")
    @PatchMapping("/{id}")
    public CarDTO patchCar(@PathVariable Long id, @RequestBody CarDTO dto) {
        return carService.patchCar(id, dto);
    }

    //  Delete car (only if current user is the owner)
    @PreAuthorize("@carSecurity.isOwner(#id)")
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
