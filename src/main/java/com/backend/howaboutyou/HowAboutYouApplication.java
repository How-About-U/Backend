package com.backend.howaboutyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HowAboutYouApplication {

    public static void main(String[] args) {
        SpringApplication.run(HowAboutYouApplication.class, args);
    }

}
