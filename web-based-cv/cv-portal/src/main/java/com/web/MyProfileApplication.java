package com.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@SpringBootApplication(scanBasePackages= {"com.web"})
@EnableEurekaClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RefreshScope
public class MyProfileApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(MyProfileApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MyProfileApplication.class);
	}
}
