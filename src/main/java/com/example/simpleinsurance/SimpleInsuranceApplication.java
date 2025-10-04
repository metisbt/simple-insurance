package com.example.simpleinsurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SimpleInsuranceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleInsuranceApplication.class, args);
    }

}
