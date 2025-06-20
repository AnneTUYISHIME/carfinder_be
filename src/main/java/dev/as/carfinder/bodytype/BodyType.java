package dev.as.carfinder.bodytype;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

    @Entity
    @Table(name = "body_types")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class BodyType {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private String image;

        private LocalDateTime createdAt = LocalDateTime.now();

    }




