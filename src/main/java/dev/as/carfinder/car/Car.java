package dev.as.carfinder.car;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import dev.as.carfinder.user.User;
import dev.as.carfinder.brand.Brand;
import dev.as.carfinder.BodyType.BodyType;
import dev.as.carfinder.review.Review;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate manufactureDate;

    private Double price;

    private String images;

    private String location;

    private String driveType;

    private String engine;

    @Column(length = 1000)
    private String description;

    @Column(length = 1000)
    private String features;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "body_type_id")
    private BodyType bodyType;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Review> reviews;

    // Getters & Setters
}