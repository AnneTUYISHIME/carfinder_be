package dev.as.carfinder.user;

import jakarta.persistence.*;
import java.util.List;
import dev.as.carfinder.user.User;
import dev.as.carfinder.car.Car;
import dev.as.carfinder.review.Review;
import dev.as.carfinder.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fname;
    private String lname;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Relationships
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Car> cars; // One user can sell many cars

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews; // One user can make many reviews

    // Getters & Setters
}
