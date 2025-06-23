package dev.as.carfinder.DTos;





import dev.as.carfinder.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

    @Data
    public class UserCreationDto {

        @NotBlank(message = "First name is required")
        private String fname;

        @NotBlank(message = "Last name is required")
        private String lname;

        @Email(message = "Invalid email format")
        @NotBlank(message = "Email is required")
        private String email;

        @NotBlank(message = "Phone number is required")
        private String phone;


        @NotNull
        private User.Role role;
    }


