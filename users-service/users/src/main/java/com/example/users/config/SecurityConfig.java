package com.example.users.config; // Укажите ваш пакет

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/registration").permitAll() // Разрешаем регистрацию без авторизации api/users/logout/{id}
                .requestMatchers("/api/users/login").permitAll() // Разрешаем регистрацию без авторизации http://localhost:8083/api/users/refresh/17
                .requestMatchers("/api/users/logout/{id}").permitAll()
                .requestMatchers("/api/users/refresh").permitAll()
                .anyRequest().authenticated() // Все остальные запросы требуют авторизации
            )
            .csrf(csrf -> csrf.disable()) // Отключаем CSRF-защиту (например, для Postman)
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())) // Разрешаем H2 iframe
            .httpBasic(httpBasic -> {}) // Включаем Basic Auth (необязательно настраивать)
            .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080")); // Ваш фронт-энд (или клиент)
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowCredentials(true); // Разрешаем отправку учетных данных (куки)
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Cookie"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}
}