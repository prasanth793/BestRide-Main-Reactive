package com.main.service.bestride.controller;

import com.main.service.bestride.data.FareDetails;
import com.main.service.bestride.service.BestRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@RestController
@RequestMapping("/bestride")
public class MainController {

    @Autowired
    private BestRideService bestRideService;

    @GetMapping(value = "/getFares", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<FareDetails> getAllFares(@RequestParam String origin,@RequestParam  String destination){

        return bestRideService.getAllFares(origin, destination);

    }



}
