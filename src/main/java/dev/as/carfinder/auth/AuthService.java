package dev.as.carfinder.auth;




import lombok.RequiredArgsConstructor;
import dev.as.carfinder.auth.AuthService.;
import org.devkiki.sec.auth.jwt.JwtService;
import dev.as.carfinder.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.as.carfinder.DTos.LoginDto;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    // localhost:8080/api/auth/login localhost:8080/api/auth/sign-up
    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/login") // I'll be returning the token only
    public ResponseEntity<Map<String,String>> login(@RequestBody LoginDto dto) {
        var loggedInUser = authService.loginUser(dto);
        String token = jwtService.generateToken(loggedInUser);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<User> login(@RequestBody SignupDto dto) {
        var registerUser =  authService.registerUser(dto);
        return new ResponseEntity<>(registerUser, HttpStatus.OK);
    }

    @GetMapping("test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

}




