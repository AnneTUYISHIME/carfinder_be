package dev.as.carfinder.BodyType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BodyTypeDTO {
    private Long id;
    private String name;
    private String image;

    // Getters & Setters
}