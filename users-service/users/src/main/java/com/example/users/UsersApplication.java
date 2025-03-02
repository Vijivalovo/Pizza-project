package com.example.users;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(UsersApplication.class);
        app.setDefaultProperties(Map.of("server.port", "8081"));
        app.run(args);
	}

}
