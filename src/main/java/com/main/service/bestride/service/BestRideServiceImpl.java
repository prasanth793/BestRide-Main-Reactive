package com.main.service.bestride.service;

import com.main.service.bestride.data.FareDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Random;

@Service
public class BestRideServiceImpl implements BestRideService{

    @Autowired
    @Qualifier("backend")
    private WebClient webClient;

    @Override
    public Flux<FareDetails> getAllFares(String origin, String destination, Integer pincode, Double distance) {

        return Flux.merge(webClient.get()
                .uri(String.format("/uber/getUberFare?origin=%s&destination=%s&pincode=%s&distance=%s",origin, destination, pincode, distance))
                .retrieve()
                .bodyToFlux(FareDetails.class),webClient.get()
                .uri(String.format("/ola/getOlaFare?origin=%s&destination=%s&pincode=%s&distance=%s", origin, destination, pincode, distance))
                .retrieve().bodyToFlux(FareDetails.class), webClient.get()
                .uri(String.format("/rapido/getRapidoFare?origin=%s&destination=%s&pincode=%s&distance=%s", origin, destination, pincode, distance))
                .retrieve().bodyToFlux(FareDetails.class));

    }

    public Flux<FareDetails> getLiveFaresWithUpdatedCost(String origin, String destination, Integer pincode, Double distance){

        return Flux.interval(Duration.ofMillis(0),Duration.ofSeconds(10))
                .take(Duration.ofSeconds(120))
                .flatMap(aLong -> getAllFares(origin, destination, pincode, distance)
                        .map(fareDetails -> {
                            fareDetails.setCost(fareDetails.getCost() + new Random().nextDouble(10.5));
                            return fareDetails;
                        }));

    }
}
