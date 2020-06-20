package com.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.retry.annotation.EnableRetry;

@EnableEurekaClient
@SpringBootApplication(scanBasePackages= {"com.web"})
@RefreshScope
@EnableRetry
public class AuthServiceApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(AuthServiceApplication.class, args);
	}
}
