package dev.as.carfinder.user;





import dev.as.carfinder.DTos.UserCreationDto;
import dev.as.carfinder.user.User.Role;
import dev.as.carfinder.user.User;
import dev.as.carfinder.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

    @Service
    @RequiredArgsConstructor
    public class UserService {

        private final UserRepository userRepository;

        //  Create User from DTO → Save to DB → Return Entity
        public User createUser(UserCreationDto dto) {
            User user = User.builder()
                    .fname(dto.getFname())
                    .lname(dto.getLname())
                    .email(dto.getEmail())
                    .phone(dto.getPhone())
                    .role(Role.BUYER) // Default role → you can change to pass from DTO if you want
                    .build();

            return userRepository.save(user);
        }

        //  Get all users from DB
        public List<User> getAllUsers() {
            return userRepository.findAll();
        }

        //  Get one user by ID → Optional (can be empty or present)
        public Optional<User> getUserById(Long id) {
            return userRepository.findById(id);
        }

        //  Delete user by ID
        public void deleteUser(Long id) {
            userRepository.deleteById(id);
        }
    }


