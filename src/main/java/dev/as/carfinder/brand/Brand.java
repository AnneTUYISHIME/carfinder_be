package dev.as.carfinder.brand;

import jakarta.persistence.*;
<<<<<<< ft-brand-car
import java.util.List;
import dev.as.carfinder.car.Car;
import lombok.*;


@Entity
@Table(name = "brands")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

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
=======






    @Entity
    @Table(name = "brands")
    public class Brand {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true, nullable = false)
        private String name;
    }



>>>>>>> main
