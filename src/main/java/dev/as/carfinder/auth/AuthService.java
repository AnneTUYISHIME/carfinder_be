package dev.as.carfinder.auth;

import dev.as.carfinder.auth.dto.LoginDto;
import dev.as.carfinder.auth.dto.RegisterDto;
import dev.as.carfinder.auth.jwt.JwtService;
import dev.as.carfinder.email.MailService;
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
    private final MailService mailService;



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

    public String forgotPassword(String email) {
        // Step 1: Get user by email
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Step 2: Generate reset token with user info
        String token = jwtService.generateResetPasswordToken(user);

        // Step 3: Build password reset link for frontend
        String resetLink = "http://localhost:5173/reset-password?token=" + token;

        // Step 4: Create the HTML email content
        String html = "<p>Hello <b>" + user.getFirstName() + "</b>,</p>"
                + "<p>You requested a password reset. Click the link below:</p>"
                + "<p><a href=\"" + resetLink + "\">Reset Password</a></p>"
                + "<br><p>If you didn't request this, you can ignore this email.</p>";

        // Step 5: Send email
        mailService.send(user.getEmail(), "Reset Your Password", html);

        // Step 6: Return message
        return "Password reset link has been sent to your email.";
    }


    public void resetPassword(String token, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match.");
        }

        String email = jwtService.getUsername(token);

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
    }

}
