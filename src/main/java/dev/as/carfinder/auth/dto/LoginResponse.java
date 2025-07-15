package dev.as.carfinder.auth.dto;
import dev.as.carfinder.user.dto.UserResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String message;
    private String token;
    private UserResponseDto user;
}
