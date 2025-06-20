package dev.as.carfinder.brand;

import jakarta.persistence.*;






    @Entity
    @Table(name = "brands")
    public class Brand {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true, nullable = false)
        private String name;
    }



