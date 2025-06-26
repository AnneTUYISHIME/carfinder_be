package dev.as.carfinder.car;

import dev.as.carfinder.brand.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CarDTO {
    private Long id;
    private String name;
    private LocalDate manufactureDate;
    private Double price;
    private String images;
    private String location;
    private String driveType;
    private String engine;
    private String description;
    private List<String> features;
    private Long brandId;
    private Long bodyTypeId;
    private Long sellerId;



    // Getters & Setters
}
