package dev.as.carfinder.car;
import dev.as.carfinder.review.ReviewDTO;
import java.util.List;

public interface CarService {
    CarDTO createCar(CarDTO carDTO);
    CarDTO getCarById(Long id);
    List<CarDTO> getAllCars();
    List<CarDTO> getCarsByOwnerEmail(String email);

    CarDTO updateCar(Long id, CarDTO carDTO);
    CarDTO patchCar(Long id, CarDTO carDTO);
    void deleteCar(Long id);
    List<CarDTO> getCarsByOwner(Long ownerId);
    List<CarDTO> filterCars(Long brandId, Long bodyTypeId, Double minPrice, Double maxPrice, String location, String driveType, String condition);// NEW

    List<ReviewDTO> getReviewsForCar(Long carId);

    ReviewDTO addReviewToCar(ReviewDTO dto);

    ReviewDTO getReviewById(Long id);
}


