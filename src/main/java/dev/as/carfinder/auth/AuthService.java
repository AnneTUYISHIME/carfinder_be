package dev.as.carfinder.Auth;

import dev.as.carfinder.DTos.LoginDto;
import dev.as.carfinder.DTos.LoginResponseDto;
import dev.as.carfinder.DTos.RegisterRequestDto;
import dev.as.carfinder.jwt.JwtService;
import dev.as.carfinder.user.User.Role;
import dev.as.carfinder.user.User;
import dev.as.carfinder.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Transactional
    public User register(RegisterRequestDto dto) {
        if (!dto.password().equals(dto.confirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match.");
        }

        if (userRepository.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("Email already taken.");
        }

        User user = User.builder()
                .fname(dto.firstName())
                .lname(dto.lastName())
                .email(dto.email())
                .address(dto.address())
                .phone(dto.phone())
                .role(dto.role() != null ? dto.role() : Role.BUYER)
                .password(passwordEncoder.encode(dto.password()))
                .build();

        return userRepository.save(user);
    }

    public LoginResponseDto login(LoginDto dto) {
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + dto.email()));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponseDto(
                token,
                user.getFname() + " " + user.getLname(),
                user.getEmail(),
                user.getRole().name()
        );
    }
    }

