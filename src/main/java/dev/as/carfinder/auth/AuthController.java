package dev.as.carfinder.auth;

import dev.as.carfinder.DTOs.ResetPasswordRequestDto;
import dev.as.carfinder.auth.dto.LoginDto;
import dev.as.carfinder.auth.dto.LoginResponse;
import dev.as.carfinder.auth.dto.RegisterDto;
import dev.as.carfinder.DTOs.ForgotPasswordRequestDto;
import dev.as.carfinder.user.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication Controller", description = "Authentication management endpoints")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Login endpoint")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginDto loginDto) {
        String token = authService.userLogin(loginDto);
        String message = "User logged in successfully";
        return ResponseEntity.ok(new LoginResponse(token, message));
    }

    @PostMapping("/register")
    @Operation(summary = "Register endpoint")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody RegisterDto registerDto) {
        var regResp = authService.registerUser(registerDto);
        return ResponseEntity.ok(regResp);
    }

    @SecurityRequirement(name = "auth")
    @GetMapping("/me")
    @Operation(summary = "Get current logged in user")
    public ResponseEntity<UserResponseDto> getCurrentUser() {
        var loggedInUser = authService.getAuthenticatedUser();
        return ResponseEntity.ok(loggedInUser);
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@Valid @RequestBody ForgotPasswordRequestDto request) {
        return authService.forgotPassword(request.email());
    }


    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody ResetPasswordRequestDto request) {
        authService.resetPassword(request.token(), request.newPassword(), request.confirmPassword());
        return "Password reset successfully.";
    }


}
