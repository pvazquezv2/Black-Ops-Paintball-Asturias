package com.pelayo.paintball;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.pelayo")
@EntityScan(basePackages = "com.pelayo.model")
@EnableJpaRepositories(basePackages = "com.pelayo.repository")
public class PaintballmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaintballmanagerApplication.class, args);
    }

}
