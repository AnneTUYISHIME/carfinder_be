package dev.as.carfinder.brand;

import jakarta.persistence.*;
import java.util.List;
import dev.as.carfinder.car.Car;

@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Car> cars; // One brand has many cars

    // Getters & Setters
}