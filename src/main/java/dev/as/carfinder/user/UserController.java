package dev.as.carfinder.user;

import dev.as.carfinder.user.dto.CreateUserRequest;
import dev.as.carfinder.user.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "auth")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users Controller", description = "A User management Controller")
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create user endpoint")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody CreateUserRequest req) {
        var createdUser = userService.create(req);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all users endpoint")
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by id")
    public ResponseEntity<UserResponseDto> findByEmail(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user by id")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
