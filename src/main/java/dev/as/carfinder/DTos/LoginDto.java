package dev.as.carfinder.DTos;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank(message = "Email is required") @Email String email,
        @NotBlank(message = "Password is required") String password
) {
}

