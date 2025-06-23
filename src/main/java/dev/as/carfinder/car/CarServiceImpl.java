package dev.as.carfinder.car;

import dev.as.carfinder.car.CarDTO;
import dev.as.carfinder.car.Car;
import dev.as.carfinder.user.User;
import dev.as.carfinder.brand.Brand;
import dev.as.carfinder.BodyType.BodyType;
import dev.as.carfinder.car.CarRepository;
import dev.as.carfinder.brand.BrandRepository;
import dev.as.carfinder.BodyType.BodyTypeRepository;


import dev.as.carfinder.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BodyTypeRepository bodyTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CarDTO createCar(CarDTO dto) {
        Car car = new Car();
        mapDtoToEntity(dto, car);
        carRepository.save(car);
        return mapEntityToDto(car);
    }

    @Override
    public CarDTO getCarById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        return mapEntityToDto(car);
    }

    @Override
    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public CarDTO updateCar(Long id, CarDTO dto) {
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        mapDtoToEntity(dto, car);
        carRepository.save(car);
        return mapEntityToDto(car);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    // Helper Methods

    private CarDTO mapEntityToDto(Car car) {
        CarDTO dto = new CarDTO();
        dto.setId(car.getId());
        dto.setName(car.getName());
        dto.setPrice(car.getPrice());
        dto.setManufactureDate(car.getManufactureDate());
        dto.setImages(car.getImages());
        dto.setLocation(car.getLocation());
        dto.setDriveType(car.getDriveType());
        dto.setEngine(car.getEngine());
        dto.setDescription(car.getDescription());

        // Split comma-separated features into list
        dto.setFeatures(List.of(car.getFeatures().split(",")));

        dto.setBrandId(car.getBrand().getId());
        dto.setBodyTypeId(car.getBodyType().getId());
        dto.setSellerId(car.getSeller().getId());
        return dto;
    }

    private void mapDtoToEntity(CarDTO dto, Car car) {
        car.setName(dto.getName());
        car.setPrice(dto.getPrice());
        car.setManufactureDate(dto.getManufactureDate());
        car.setImages(dto.getImages());
        car.setLocation(dto.getLocation());
        car.setDriveType(dto.getDriveType());
        car.setEngine(dto.getEngine());
        car.setDescription(dto.getDescription());

        // Convert List<String> to comma-separated String
        car.setFeatures(String.join(",", dto.getFeatures()));

        // Set Relationships
        car.setBrand(brandRepository.findById(dto.getBrandId()).orElseThrow(() -> new RuntimeException("Brand not found")));
        car.setBodyType(bodyTypeRepository.findById(dto.getBodyTypeId()).orElseThrow(() -> new RuntimeException("BodyType not found")));
        car.setSeller(userRepository.findById(dto.getSellerId()).orElseThrow(() -> new RuntimeException("User not found")));
    }
}

