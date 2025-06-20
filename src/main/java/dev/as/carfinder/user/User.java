package dev.as.carfinder.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;






import jakarta.persistence.*;
import lombok.*;
import dev.as.carfinder.car.Car;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fname;
    private String lname;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt = LocalDateTime.now();

    // One User can have Many Cars (the cars table will have a 'user_id' column)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars;

    public enum Role {
        ADMIN,
        SELLER,
        BUYER
    }
}



