package com.web.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WebProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebProxyApplication.class, args);
    }

//    @Bean
//    public PortalPreFilter preFilter() {
//        return new PortalPreFilter();
//    }
}
