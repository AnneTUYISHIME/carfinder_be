package dev.as.carfinder.car;

import dev.as.carfinder.car.CarDTO;
import dev.as.carfinder.car.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public CarDTO createCar(@RequestBody CarDTO dto) {
        return carService.createCar(dto);
    }

    @GetMapping("/{id}")
    public CarDTO getCar(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @GetMapping
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @PutMapping("/{id}")
    public CarDTO updateCar(@PathVariable Long id, @RequestBody CarDTO dto) {
        return carService.updateCar(id, dto);
    }

    @PatchMapping("/{id}")
    public CarDTO patchCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
        return carService.patchCar(id, carDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
