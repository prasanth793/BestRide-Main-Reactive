package com.main.service.bestride.controller;

import com.main.service.bestride.client.MapsClient;
import com.main.service.bestride.data.FareDetails;
import com.main.service.bestride.service.BestRideService;
import com.main.service.bestride.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@RestController
@RequestMapping("/bestride")
public class MainController {

    @Autowired
    private BestRideService bestRideService;

    @Autowired
    private MapsClient mapsClient;

    @Autowired
    private GeminiService geminiService;

    @GetMapping(value = "/getFares", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<FareDetails> getAllFares(@RequestParam String origin,@RequestParam  String destination){
        System.out.println("MainController::getAllFares::origin::"+origin);
        System.out.println("MainController::getAllFares::destination::"+destination);

        return Mono.zip(geminiService.getPincode(destination),mapsClient.getDistance(origin,destination))
                .flatMapMany(tuple -> {
                    System.out.println("MainController::getAllFares::pincode::"+tuple.getT1());
                    System.out.println("MainController::getAllFares::distance::"+tuple.getT2().getRows().getFirst().getElements().getFirst().getDistance().getValue());
                   return bestRideService.getLiveFaresWithUpdatedCost(origin,destination,tuple.getT1(),tuple.getT2().getRows().getFirst().getElements().getFirst().getDistance().getValue());
                });

    }



}
