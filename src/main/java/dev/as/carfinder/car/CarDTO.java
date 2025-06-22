package dev.as.carfinder.car;

import java.time.LocalDate;
import java.util.List;

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
