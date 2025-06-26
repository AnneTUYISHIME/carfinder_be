package dev.as.carfinder.auth.jwt;

import dev.as.carfinder.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {
    @Value("${jwt.secret_key}")
    private String SECRET_KEY;
    @Value("${jwt.expiration}")
    private Long EXPIRATION_TIME;

    public String generateToken(UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = (User) userDetails;
        List<String> roles = List.of("ROLE_"+user.getRole().name());
        return Jwts.builder()
                .subject(username)
                .claim("full_name", user.getFirstName() + user.getLastName())
                .claim("email", user.getEmail())
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date( System.currentTimeMillis()+ getExpirationTime()))
                .signWith(getKey())
                .compact();
    }

    public Claims getClaims (String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }

    public String getUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public List<String> getRoles(String token) {
        return getClaim(token, claims -> {
            Object roles = claims.get("roles");
            if (roles instanceof List<?> roleList){
                return roleList.stream()
                        .filter(String.class::isInstance)
                        .map(String.class::cast)
                        .collect(Collectors.toList());
            }

            return List.of();
        });
    }

    public Date getExpirationTime(String token) {
        return getClaim(token, Claims::getExpiration);
    }


    public SecretKey getKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public Long getExpirationTime() {
        return EXPIRATION_TIME;
    }

    public boolean isTokenExpired(String token) {
        return getExpirationTime(token).before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }


}
