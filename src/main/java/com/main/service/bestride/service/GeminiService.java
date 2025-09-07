package com.main.service.bestride.service;

import reactor.core.publisher.Mono;

public interface GeminiService {
    Mono<Integer> getPincode(String destination);
}
