package dev.as.carfinder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizationManagerRequestMatcherRegistry = http
                .csrf().disable() // Disable CSRF for testing with Postman
                .authorizeHttpRequests()
                .requestMatchers("/api/**").permitAll() // Allow all API access
                .anyRequest().permitAll();// Or restrict other paths if needed

        return http.build();
    }
}
