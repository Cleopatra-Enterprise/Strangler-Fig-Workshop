package com.ces.slc.workshop.breakdown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.ces.slc.workshop")
@EnableJpaRepositories("com.ces.slc.workshop")
@EntityScan("com.ces.slc.workshop")
public class BreakdownServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BreakdownServiceApplication.class, args);
    }
}
