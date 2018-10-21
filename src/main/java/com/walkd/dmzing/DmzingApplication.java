package com.walkd.dmzing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DmzingApplication {
    public static void main(String[] args) {
        SpringApplication.run(DmzingApplication.class, args);
    }
}
