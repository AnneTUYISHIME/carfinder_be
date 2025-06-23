package dev.as.carfinder.DTos;





import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class ProfileCreationDto {
        @NotBlank
        @Length(min = 5, max = 100)
        private String fullName;
        private String profilePicture;
        @Positive(message = "User id should be a valid number")
        private long userId;
    }




