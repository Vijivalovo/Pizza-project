package com.example.products;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductsApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ProductsApplication.class);
        app.setDefaultProperties(Map.of("server.port", "8082"));
        app.run(args);
	}

}
