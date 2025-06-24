package dev.as.carfinder.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



    @Repository
    public interface UserRepository extends JpaRepository<User, Long> {


        Optional<dev.as.carfinder.user.User> findByEmail(String email);
}
