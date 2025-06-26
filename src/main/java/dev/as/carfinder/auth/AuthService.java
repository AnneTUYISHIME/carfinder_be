package dev.as.carfinder.auth;

import dev.as.carfinder.auth.dto.LoginDto;
import dev.as.carfinder.auth.dto.RegisterDto;
import dev.as.carfinder.auth.jwt.JwtService;
import dev.as.carfinder.exception.ResourceNotFound;
import dev.as.carfinder.user.User;
import dev.as.carfinder.user.UserRepository;
import dev.as.carfinder.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepo;
    private final ModelMapper modelMapper;

    public UserResponseDto registerUser(RegisterDto dto) {
        var user = modelMapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsDeleted(false);
        var createdUser = userRepo.save(user);
        return modelMapper.map(createdUser, UserResponseDto.class);
    }

    public String userLogin(LoginDto dto) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
        var authenticatedUser = userRepo.findByEmail(dto.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return jwtService.generateToken(authenticatedUser);
    }

    public UserResponseDto getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String userName = auth.getName();
            System.out.println(userName);
            var user = userRepo.findByEmail(userName).orElseThrow(() -> new ResourceNotFound("User not found"));
            return modelMapper.map(user, UserResponseDto.class);
        } else {
            throw new RuntimeException("User not found");
        }
    }

}
