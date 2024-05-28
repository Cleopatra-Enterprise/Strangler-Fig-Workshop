package com.ces.slc.workshop.estimating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.ces.slc.workshop")
@EntityScan("com.ces.slc.workshop")
public class EstimatingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstimatingServiceApplication.class, args);
    }
}
