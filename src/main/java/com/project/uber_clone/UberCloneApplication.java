package com.project.uber_clone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class UberCloneApplication {

	public static void main(String[] args) {

		SpringApplication.run(UberCloneApplication.class, args);
		System.out.println("The start of big Journey!");
	}

}
