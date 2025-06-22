package dev.as.carfinder.BodyType;

import jakarta.persistence.*;
import java.util.List;
import dev.as.carfinder.car.Car;

@Entity
@Table(name = "body_types")
public class BodyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image; // Link to image

    @OneToMany(mappedBy = "bodyType", cascade = CascadeType.ALL)
    private List<Car> cars; // One body type used by many cars

    // Getters & Setters
}