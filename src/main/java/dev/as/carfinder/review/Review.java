package dev.as.carfinder.review;

import jakarta.persistence.*;
import dev.as.carfinder.car.Car;
import dev.as.carfinder.user.User;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String review;

    private Integer stars;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    // Getters & Setters
}
