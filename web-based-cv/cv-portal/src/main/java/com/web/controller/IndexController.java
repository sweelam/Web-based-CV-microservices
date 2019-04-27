package com.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class IndexController {
    @Value("${testValue}")
    private String testValue;

    @RequestMapping("/home")
    public String index() {
        return "index.html";
    }

    @GetMapping("/test")
    public String getRefreshedValue() {
        return testValue;
    }
}
