package com.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
//@PropertySource("application-${spring.profiles.active}.properties")
@SpringBootApplication(scanBasePackages= {"com.web"})
public class MyProfileApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MyProfileApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MyProfileApplication.class);
	}
}
