package dev.as.carfinder.review;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CreateReviewDTO {
    private Integer stars;
    private String comment;
    private Double rating;
}

