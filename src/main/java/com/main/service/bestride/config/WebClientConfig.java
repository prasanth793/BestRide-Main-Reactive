package com.main.service.bestride.config;

import io.netty.channel.ChannelOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient WebClient(){
        return WebClient.builder().baseUrl("http://localhost:8081")
                .build();
    }

    @Bean("backend")
    public WebClient backendWebClient() {
        ConnectionProvider provider = ConnectionProvider.builder("custom")
                .maxConnections(100)
                .maxIdleTime(Duration.ofSeconds(20))
                .maxLifeTime(Duration.ofSeconds(60))
                .pendingAcquireTimeout(Duration.ofSeconds(60))
                .evictInBackground(Duration.ofSeconds(120))
                .build();

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create(provider)
                                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                                .responseTimeout(Duration.ofSeconds(30))
                ))
                .baseUrl("http://localhost:8081")
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
