package dev.as.carfinder.DTos;


import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequestDto(
        @NotBlank(message = "Reset token is required") String token,
        @NotBlank(message = "New password is required") String newPassword,
        @NotBlank(message = "Confirm new password is required") String confirmPassword
) {}


