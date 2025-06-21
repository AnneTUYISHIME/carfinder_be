package dev.as.carfinder.DTos;





import lombok.AllArgsConstructor;
import lombok.Data;

    @Data
    @AllArgsConstructor
    public class UserResponseDto {
        private Long id;
        private String fullName;
        private String email;
    }


