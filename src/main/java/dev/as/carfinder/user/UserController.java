package dev.as.carfinder.user;



import dev.as.carfinder.DTos.UserCreationDto;
import dev.as.carfinder.DTos.UserResponseDto;
import dev.as.carfinder.user.User;
//import dev.as.carfinder.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.as.carfinder.DTos.ProfileCreationDto;

import java.util.List;

    @RestController
    @RequestMapping("/api/users")
    @RequiredArgsConstructor
    public class UserController {

        private final UserService userService;

        //  CREATE user with validation and DTOs
        @PostMapping
        public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserCreationDto dto) {
            User savedUser = userService.createUser(dto);
            String fullName = savedUser.getFname() + " " + savedUser.getLname();
            return new ResponseEntity<>(new UserResponseDto(savedUser.getId(), fullName, savedUser.getEmail()), HttpStatus.CREATED);
        }


        @GetMapping
        public ResponseEntity<List<User>> getAllUsers() {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        }

        // GET user by ID
        @GetMapping("/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Long id) {
            return userService.getUserById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        //  DELETE user by ID
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }


        @PostMapping("/profile")
        @Operation(
                summary = "Create a profile"
        )
        public User createProfile(@RequestBody @Valid ProfileCreationDto dto) {
            return userService.createProfile(dto);
        }



    }


