package dev.as.carfinder.DTos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordRequestDto(
        @NotBlank(message = "Email is required")
        @Email
        String email
) {}
