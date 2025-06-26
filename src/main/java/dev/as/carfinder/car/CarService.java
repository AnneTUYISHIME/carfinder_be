package dev.as.carfinder.car;

import dev.as.carfinder.car.CarDTO;

import java.util.List;

public interface CarService {
    CarDTO createCar(CarDTO carDTO);
    CarDTO getCarById(Long id);
    List<CarDTO> getAllCars();
    CarDTO updateCar(Long id, CarDTO carDTO);
    CarDTO patchCar(Long id, CarDTO carDTO);
    void deleteCar(Long id);
}
