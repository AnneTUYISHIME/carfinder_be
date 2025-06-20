package dev.as.carfinder.car;





import jakarta.persistence.*;
import java.time.LocalDate;
import dev.as.carfinder.brand.Brand;
import dev.as.carfinder.bodytype.BodyType;
import dev.as.carfinder.user.User;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    private LocalDate manufactureDate;
    private Double price;

    private String images;
    private String location;

    private String driveType;
    private String engine;
    private String description;

    @Column(length = 1000)
    private String features;

    @ManyToOne
    @JoinColumn(name = "body_type_id")
    private BodyType bodyType;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;


}


