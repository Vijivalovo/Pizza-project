package com.example.orders;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrdersApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(OrdersApplication.class);
        app.setDefaultProperties(Map.of("server.port", "8081"));
        app.run(args);
	}

}
