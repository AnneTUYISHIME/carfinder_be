package dev.as.carfinder.DTos;





import dev.as.carfinder.user.User.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

    public record RegisterRequestDto(
            @NotBlank(message = "First name is required") String firstName,
            @NotBlank(message = "Last name is required") String lastName,
            @NotBlank(message = "Email is required") @Email String email,
            @NotBlank(message = "Address is required") String address,
            @NotBlank(message = "Phone is required") String phone,
            Role role,
            @NotBlank(message = "Password is required") @Size(min = 6) String password,
            @NotBlank(message = "Confirm password is required") String confirmPassword
    ) {

    }



