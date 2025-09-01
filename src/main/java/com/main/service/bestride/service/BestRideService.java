package com.main.service.bestride.service;

import com.main.service.bestride.data.FareDetails;
import reactor.core.publisher.Flux;

public interface BestRideService {

    Flux<FareDetails> getAllFares(String origin, String destination);

}
