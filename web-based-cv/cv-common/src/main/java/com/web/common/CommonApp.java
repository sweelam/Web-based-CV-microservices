package com.web.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.web"})
public class CommonApp {
    public static void main(String[] args) {
        SpringApplication.run(CommonApp.class, args);
    }
}
