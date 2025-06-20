package dev.as.carfinder.review;

import jakarta.persistence.*;
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






