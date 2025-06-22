package dev.as.carfinder.user;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

    @Entity
    @Getter
    @Setter
    @Table(name = "profiles")
    public class Profile {
        @Id
        @GeneratedValue
        private long id;
        @Column(name = "full_name")
        private String fullName;
        @Column(name = "profile_pic")
        private String profilePicture;
    }

