package com.example.users;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(UsersApplication.class);
        app.setDefaultProperties(Map.of("server.port", "8083"));
        app.run(args);
	}

}
