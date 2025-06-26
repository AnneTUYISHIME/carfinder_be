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






import jakarta.persistence.*;
import lombok.*;
import dev.as.carfinder.car.Car;

import java.time.LocalDateTime;
import java.util.List;

@Entity
<<<<<<< ft-brand-car
@Table(name = "users")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
=======
@Table(name = "user_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
>>>>>>> main
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fname;
    private String lname;

<<<<<<< ft-brand-car
    @Column(nullable = false, unique = true)
=======
    @Column(unique = true, nullable = false)
>>>>>>> main
    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

<<<<<<< ft-brand-car
    // Relationships
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Car> cars; // One user can sell many cars

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews; // One user can make many reviews

    // Getters & Setters
=======
    private LocalDateTime createdAt = LocalDateTime.now();

    // One User can have Many Cars (the cars table will have a 'user_id' column)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars;

    public enum Role {
        ADMIN,
        SELLER,
        BUYER
    }
>>>>>>> main
}



