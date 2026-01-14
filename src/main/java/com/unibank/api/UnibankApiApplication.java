package com.unibank.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.unibank.api")
public class UnibankApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnibankApiApplication.class, args);
	}

}
