package dev.as.carfinder.bodytype;

import dev.as.carfinder.car.Car;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "body_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BodyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    @OneToMany(mappedBy = "bodyType", cascade = CascadeType.ALL)
    private List<Car> cars;

}