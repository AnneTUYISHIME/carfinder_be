package dev.as.carfinder.car;

import dev.as.carfinder.bodytype.BodyTypeRepository;
import dev.as.carfinder.brand.BrandRepository;
import dev.as.carfinder.user.User;
import dev.as.carfinder.user.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final BodyTypeRepository bodyTypeRepository;
    private final UserRepository userRepository;

    @Override
    public CarDTO createCar(CarDTO dto) {
        var car = mapDtoToEntity(dto);
        car.setOwner(getCurrentUser());
        carRepository.save(car);
        return mapEntityToDto(car);
    }

    @Override
    public CarDTO getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        return mapEntityToDto(car);
    }

    @Override
    public List<CarDTO> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarDTO updateCar(Long id, CarDTO dto) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        if (!isOwner(car)) throw new RuntimeException("You are not the owner of this car.");

        mapDtoToEntity(dto);
        carRepository.save(car);
        return mapEntityToDto(car);
    }

    @Override
    public CarDTO patchCar(Long id, CarDTO dto) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        if (!isOwner(car)) throw new RuntimeException("You are not the owner of this car.");

        if (dto.getName() != null) car.setName(dto.getName());
        if (dto.getPrice() != null) car.setPrice(dto.getPrice());
        if (dto.getManufactureDate() != null) car.setManufactureDate(dto.getManufactureDate());
        if (dto.getLocation() != null) car.setLocation(dto.getLocation());
        if (dto.getDriveType() != null) car.setDriveType(dto.getDriveType());
        if (dto.getEngine() != null) car.setEngine(dto.getEngine());
        if (dto.getDescription() != null) car.setDescription(dto.getDescription());
        if (dto.getFeatures() != null) car.setFeatures(String.join(",", dto.getFeatures()));
        if (dto.getImages() != null) car.setImages(dto.getImages());

        if (dto.getBrandId() != null) {
            car.setBrand(brandRepository.findById(dto.getBrandId())
                    .orElseThrow(() -> new RuntimeException("Brand not found")));
        }

        if (dto.getBodyTypeId() != null) {
            car.setBodyType(bodyTypeRepository.findById(dto.getBodyTypeId())
                    .orElseThrow(() -> new RuntimeException("BodyType not found")));
        }

        carRepository.save(car);
        return mapEntityToDto(car);
    }

    @Override
    public void deleteCar(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        if (!isOwner(car)) throw new RuntimeException("You are not the owner of this car.");

        carRepository.delete(car);
    }

    @Override
    public List<CarDTO> getCarsByOwner(Long ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        return carRepository.findByOwner(owner)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarDTO> getCarsByOwnerEmail(String email) {
        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return carRepository.findByOwner(owner)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<CarDTO> filterCars(Long brandId, Long bodyTypeId, Double minPrice, Double maxPrice, String location, String driveType) {
        Specification<Car> spec = Specification
                .where(CarSpecifications.hasBrand(brandId))
                .and(CarSpecifications.hasBodyType(bodyTypeId))
                .and(CarSpecifications.priceBetween(minPrice, maxPrice))
                .and(CarSpecifications.locationLike(location))
                .and(CarSpecifications.driveTypeEquals(driveType));

        return carRepository.findAll(spec).stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    private Car mapDtoToEntity(CarDTO dto) {
        Car car = new Car();
        car.setName(dto.getName());
        car.setPrice(dto.getPrice());
        car.setManufactureDate(dto.getManufactureDate());
        car.setImages(dto.getImages());
        car.setLocation(dto.getLocation());
        car.setDriveType(dto.getDriveType());
        car.setEngine(dto.getEngine());
        car.setDescription(dto.getDescription());
        car.setFeatures(String.join(",", dto.getFeatures()));

        car.setBrand(brandRepository.findById(dto.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found")));
        car.setBodyType(bodyTypeRepository.findById(dto.getBodyTypeId())
                .orElseThrow(() -> new RuntimeException("BodyType not found")));

        return car;
    }

    private CarDTO mapEntityToDto(Car car) {
        return CarDTO.builder()
                .id(car.getId())
                .name(car.getName())
                .price(car.getPrice())
                .manufactureDate(car.getManufactureDate())
                .location(car.getLocation())
                .images(car.getImages())
                .driveType(car.getDriveType())
                .engine(car.getEngine())
                .description(car.getDescription())
                .features(car.getFeatures().split(","))
                .brandId(car.getBrand().getId())
                .bodyTypeId(car.getBodyType().getId())
//                .ownerId(car.getOwner().getId())
                .build();
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));
    }

    private boolean isOwner(Car car) {
        return car.getOwner().getEmail().equals(getCurrentUser().getEmail());
    }
}
