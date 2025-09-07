package com.main.service.bestride.service;

import com.main.service.bestride.data.FareDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class BestRideServiceImpl implements BestRideService{

    @Autowired
    @Qualifier("backend")
    private WebClient webClient;

    @Override
    public Flux<FareDetails> getAllFares(String origin, String destination, Integer pincode, Double distance) {
        return webClient.get()
                .uri(String.format("/uber/getUberFare?origin=%s&destination=%s&pincode=%s&distance=%s",origin, destination, pincode, distance))
                .retrieve()
                .bodyToFlux(FareDetails.class)
                .mergeWith(webClient.get()
                        .uri(String.format("/ola/getOlaFare?origin=%s&destination=%s&pincode=%s&distance=%s", origin, destination, pincode, distance))
                        .retrieve().bodyToFlux(FareDetails.class))
                .mergeWith(webClient.get()
                        .uri(String.format("/rapido/getRapidoFare?origin=%s&destination=%s&pincode=%s&distance=%s", origin, destination, pincode, distance))
                        .retrieve().bodyToFlux(FareDetails.class));

    }
}
