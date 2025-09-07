package com.main.service.bestride.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean("backend")
    public WebClient WebClient(){
        return WebClient.builder().baseUrl("http://localhost:8081")
                .build();
    }

    @Bean("maps")
    public WebClient mapsWebClient(){
        return WebClient.builder().baseUrl("https://maps.googleapis.com/maps/api/distancematrix/json")
                .build();
    }

    @Bean("gemini")
    public WebClient geminiWebClient(){
        return WebClient.builder().baseUrl("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent")
                .build();
    }

}
