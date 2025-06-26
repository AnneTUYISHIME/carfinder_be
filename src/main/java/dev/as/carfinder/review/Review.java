package dev.as.carfinder.review;

import jakarta.persistence.*;
<<<<<<< ft-brand-car
import dev.as.carfinder.car.Car;
import dev.as.carfinder.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reviews")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import dev.as.carfinder.user.User;






    @Entity
    @Table(name = "reviews_table")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Review {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne // Many reviews can belong to one user
        @JoinColumn(name = "user_id") // Foreign key column for user
        private User user;

        private Long carId;

        private String review;

        private Integer stars;

        private LocalDateTime createdAt = LocalDateTime.now();
    }






>>>>>>> main
