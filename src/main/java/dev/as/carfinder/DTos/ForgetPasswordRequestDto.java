package dev.as.carfinder.DTos;






import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

    public record ForgetPasswordRequestDto(
            @NotBlank(message = "Email is required") @Email String email
    ) {}


