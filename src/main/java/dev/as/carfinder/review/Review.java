package dev.as.carfinder.review;

import jakarta.persistence.*;
import dev.as.carfinder.car.Car;
import dev.as.carfinder.user.User;
import lombok.*;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer stars;
    private  String comment;
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;
}







