package dev.as.carfinder.review;

import lombok.*;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {
    private Long id;
    private Integer stars;
    private String comment;
    private Double rating;
    private Long carId;
    private Long ownerId;

   // public void setId(Long id) {
    //}
}
