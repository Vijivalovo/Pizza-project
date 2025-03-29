package com.example.users.config; // Укажите ваш пакет

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/registration/").permitAll() // Разрешаем регистрацию без авторизации api/users/logout/{id}
                .requestMatchers("/api/users/login/").permitAll() // Разрешаем регистрацию без авторизации
                .requestMatchers("/api/users/logout/{id}").permitAll()
                .anyRequest().authenticated() // Все остальные запросы требуют авторизации
            )
            .csrf(csrf -> csrf.disable()) // Отключаем CSRF-защиту (например, для Postman)
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())) // Разрешаем H2 iframe
            .httpBasic(httpBasic -> {}) // Включаем Basic Auth (необязательно настраивать)
            .build();
    }
}