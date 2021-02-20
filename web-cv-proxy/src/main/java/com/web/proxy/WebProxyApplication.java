package com.web.proxy;

import com.web.proxy.filters.PortalPreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableAuthorizationServer
public class WebProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebProxyApplication.class, args);
    }

    @Bean
    public PortalPreFilter preFilter() {
        return new PortalPreFilter();
    }
}
