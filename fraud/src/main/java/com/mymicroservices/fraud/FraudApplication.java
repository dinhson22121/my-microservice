package com.mymicroservices.fraud;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FraudApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FraudApplication.class);
//        app.setBannerMode(Banner.Mode.OFF); // Disable banner
        app.run(args);
    }
}
