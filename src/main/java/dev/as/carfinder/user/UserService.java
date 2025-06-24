package dev.as.carfinder.user;





import dev.as.carfinder.DTos.UserCreationDto;
import dev.as.carfinder.user.User.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import dev.as.carfinder.DTos.ProfileCreationDto;
import dev.as.carfinder.exception.ResourceNotFound;

import java.util.List;
import java.util.Optional;


    @Service
    @RequiredArgsConstructor
    public class UserService {

        private final UserRepository userRepository;
        private final ProfileRepository ProfileRepository;


        public User createUser(UserCreationDto dto) {
            User user = User.builder()
                    .fname(dto.getFname())
                    .lname(dto.getLname())
                    .email(dto.getEmail())
                    .phone(dto.getPhone())
                    .role(dto.getRole())
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


        public void deleteUser(Long id) {
            userRepository.deleteById(id);
        }

        public User createProfile(ProfileCreationDto dto) {
            Profile profile = new Profile();
            profile.setFullName(dto.getFullName());
            profile.setProfilePicture(dto.getProfilePicture());
            Profile createProfile = ProfileRepository.save(profile);

            User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
            user.setProfile(createProfile);
            return userRepository.save(user);
        }

        public User findByEmail(String email) {
            return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFound("User not found!"));
        }
    }


