package dev.as.carfinder.Auth;




import dev.as.carfinder.Auth.AuthService;
import dev.as.carfinder.DTos.LoginDto;
import dev.as.carfinder.DTos.RegisterRequestDto;
import dev.as.carfinder.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Handles registration and authentication")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public User register(@RequestBody @Valid RegisterRequestDto dto) {
        return authService.register(dto);
    }

    @PostMapping("/login")
    @Operation(summary = "Login with email and password")
    public User login(@RequestBody @Valid LoginDto dto) {
        return authService.login(dto);
    }

}
