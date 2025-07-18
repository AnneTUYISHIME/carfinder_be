package dev.as.carfinder.car;

import dev.as.carfinder.bodytype.BodyType;
import dev.as.carfinder.brand.Brand;
import dev.as.carfinder.review.Review;
import dev.as.carfinder.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate manufactureDate;
    private Double price;
    @ElementCollection
    private List<String> images;

    private String location;
    private String driveType;
    private String engine;
    @Column(length = 1000)
    private String description;
    @Column(length = 1000)
    private String features;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "body_type_id")
    private BodyType bodyType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Review> reviews;
    @Column(nullable = false)
    private String condition;
   /* @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
   */



}
