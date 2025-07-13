package dev.as.carfinder.car;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private Long id;
    private String name;
    private Double price;
    private LocalDate manufactureDate;
    private String location;
    private String[] features;
    private List<String> images;

    private String driveType;
    private String engine;
    private String description;

    private Long brandId;
    private Long bodyTypeId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long ownerId;
    private String condition; // "new" or "used"
}
