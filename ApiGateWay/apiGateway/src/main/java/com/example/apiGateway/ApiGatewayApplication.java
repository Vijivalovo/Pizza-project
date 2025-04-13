package com.example.apiGateway;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ApiGatewayApplication.class);
        app.setDefaultProperties(Map.of("server.port", "8089"));
        app.run(args);
	}

}
