package org.uma_gym.uma_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Lista putanja koje će biti javno dostupne
    private static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable());//ne koristimo states
                /*.authorizeHttpRequests(authz -> authz
                        // Dopusti pristup Swagger UI bez autentifikacije
                        .requestMatchers(SWAGGER_WHITELIST).permitAll()
                        // Svi ostali zahtjevi zahtijevaju autentifikaciju
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());*/

        return http.build();
    }
}