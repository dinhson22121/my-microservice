package com.mymicroservices.fraud;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FraudApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FraudApplication.class);
        app.run(args);
    }
}
