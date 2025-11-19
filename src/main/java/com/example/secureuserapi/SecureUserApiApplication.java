package com.example.secureuserapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.secureuserapi", "se.me.demo"})
@EnableJpaRepositories(basePackages = "se.me.demo.repository")
@EntityScan(basePackages = "se.me.demo.model")
public class SecureUserApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecureUserApiApplication.class, args);
    }
}
