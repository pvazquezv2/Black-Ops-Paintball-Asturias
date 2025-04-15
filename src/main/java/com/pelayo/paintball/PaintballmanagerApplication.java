package com.pelayo.paintball;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PaintballmanagerApplication {
	
	@Bean
	public Principal applicationStartupRunner() {
		return new Principal();
	}

	public static void main(String[] args) {
		SpringApplication.run(PaintballmanagerApplication.class, args);
	}

}
