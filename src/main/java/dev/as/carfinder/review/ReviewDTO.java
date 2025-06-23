package dev.as.carfinder.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ReviewDTO {
    private Long id;
    private String review;
    private Integer stars;
    private Long userId;
    private Long carId;

    // Getters & Setters
}
