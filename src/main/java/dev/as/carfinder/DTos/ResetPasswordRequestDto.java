package dev.as.carfinder.DTos;


import jakarta.validation.constraints.NotBlank;

    public record ResetPasswordRequestDto(
            @NotBlank String token,
            @NotBlank String newPassword
    ) {}


