package com.walkd.dmzing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DmzingApplication {


    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.properties,"
            + "classpath:aws.properties";


    public static void main(String[] args) {
        new SpringApplicationBuilder(DmzingApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }
}
