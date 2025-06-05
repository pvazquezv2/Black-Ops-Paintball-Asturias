package com.pelayo.paintball;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(scanBasePackages = "com.pelayo")
@EntityScan(basePackages = "com.pelayo.model")
@EnableJpaRepositories(basePackages = "com.pelayo.repository")
public class PaintballmanagerApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PaintballmanagerApplication.class);

		// Leer el puerto desde la variable de entorno
		Map<String, Object> props = new HashMap<>();
		String port = System.getenv("PORT");
		if (port != null) {
			props.put("server.port", port);
		}
		app.setDefaultProperties(props);
		app.run(args);
	}
}
