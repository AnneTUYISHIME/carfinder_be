package dev.as.carfinder.car;

<<<<<<< ft-brand-car
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import dev.as.carfinder.user.User;
import dev.as.carfinder.brand.Brand;
import dev.as.carfinder.BodyType.BodyType;
import dev.as.carfinder.review.Review;
import lombok.*;
import lombok.Setter;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

=======




import jakarta.persistence.*;
import java.time.LocalDate;
import dev.as.carfinder.brand.Brand;
import dev.as.carfinder.bodytype.BodyType;
import dev.as.carfinder.user.User;

@Entity
@Table(name = "cars")
>>>>>>> main
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< ft-brand-car


=======
>>>>>>> main
    private Long id;

    private String name;

<<<<<<< ft-brand-car
    private LocalDate manufactureDate;

    private Double price;

    private String images;

    private String location;

    private String driveType;

    private String engine;

    @Column(length = 1000)
=======
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    private LocalDate manufactureDate;
    private Double price;

    private String images;
    private String location;

    private String driveType;
    private String engine;
>>>>>>> main
    private String description;

    @Column(length = 1000)
    private String features;

<<<<<<< ft-brand-car
    // Relationships
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

=======
>>>>>>> main
    @ManyToOne
    @JoinColumn(name = "body_type_id")
    private BodyType bodyType;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

<<<<<<< ft-brand-car
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public void setSellerId(Long sellerId) {
    }

    public void setBodyTypeId(Long bodyTypeId) {
    }

    public void setBrandId(Long brandId) {
    }

    // Getters & Setters

}
=======

}


>>>>>>> main
