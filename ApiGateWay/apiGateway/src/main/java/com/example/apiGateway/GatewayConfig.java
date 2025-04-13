package com.example.apiGateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("login-service", r -> r.path("/api/users/login")
                .uri("http://localhost:8083"))
                
            .route("products-service", r -> r.path("/api/products/getAll/**") // Используем /** чтобы перехватывать всё после пути
                .uri("http://localhost:8082"))

            .route("validateForData-service", r -> r.path("/api/users/validateForData")
                .uri("http://localhost:8083"))

            .route("findById-service", r -> r.path("/api/users/findById")
                .uri("http://localhost:8083"))

            .route("refresh-service", r -> r.path("/api/users/refresh")
                .uri("http://localhost:8083"))

            .build();
    }
}
