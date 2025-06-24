package dev.as.carfinder.DTos;


    public record LoginResponseDto(
            String token,
            String fullName,
            String email,
            String role
    ) {
    }

