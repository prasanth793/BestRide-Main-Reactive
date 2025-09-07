package com.main.service.bestride.client;

import com.main.service.bestride.data.DistanceMatrixResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MapsClient {

    @Autowired
    @Qualifier("maps")
    private WebClient webClient;


    public Mono<DistanceMatrixResponse> getDistance(String origin, String destination){
       return webClient
               .get()
               .uri("?destinations={destination}&origins={origin}&key=AIzaSyBtisJdlbmInqRoVK4-gj6TqG4ja-q2CIA",destination,origin)
               .retrieve()
               .bodyToMono(DistanceMatrixResponse.class);
    }

}