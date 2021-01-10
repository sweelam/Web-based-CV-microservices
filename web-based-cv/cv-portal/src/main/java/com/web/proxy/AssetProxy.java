package com.web.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AssetProxy {
    private RestTemplate restTemplate;

    @Value("${app.name.value:http://localhost:5000/api/v1/logos}")
    private String assetLogosUrl;

    public AssetProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getSysLogos() {
        return restTemplate.getForObject(assetLogosUrl, String.class);
    }
}
