package dev.as.carfinder.car;

import dev.as.carfinder.bodytype.BodyTypeRepository;
import dev.as.carfinder.brand.BrandRepository;
import dev.as.carfinder.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final BodyTypeRepository bodyTypeRepository;
    private final UserRepository userRepository;

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
    public CarDTO patchCar(Long id, CarDTO carDTO) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        if (carDTO.getName() != null) car.setName(carDTO.getName());
        if (carDTO.getPrice() != null) car.setPrice(carDTO.getPrice());
        if (carDTO.getLocation() != null) car.setLocation(carDTO.getLocation());
        if (carDTO.getImages() != null) car.setImages(carDTO.getImages());
        if (carDTO.getDriveType() != null) car.setDriveType(carDTO.getDriveType());
        if (carDTO.getEngine() != null) car.setEngine(carDTO.getEngine());
        if (carDTO.getDescription() != null) car.setDescription(carDTO.getDescription());
        if (carDTO.getFeatures() != null) {
            car.setFeatures(String.join(",", carDTO.getFeatures()));
        }
        if (carDTO.getManufactureDate() != null) car.setManufactureDate(carDTO.getManufactureDate());

        if (carDTO.getBrandId() != null) {
            car.setBrand(brandRepository.findById(carDTO.getBrandId())
                    .orElseThrow(() -> new RuntimeException("Brand not found")));
        }

        if (carDTO.getBodyTypeId() != null) {
            car.setBodyType(bodyTypeRepository.findById(carDTO.getBodyTypeId())
                    .orElseThrow(() -> new RuntimeException("BodyType not found")));
        }

        if (carDTO.getSellerId() != null) {
            car.setSeller(userRepository.findById(carDTO.getSellerId())
                    .orElseThrow(() -> new RuntimeException("User not found")));
        }

        Car saved = carRepository.save(car);
        return mapEntityToDto(saved);
    }
    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    // Helper Methods

    private CarDTO mapEntityToDto(Car car) {
        CarDTO dto = new CarDTO();
       // dto.setId(car.getId());
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

