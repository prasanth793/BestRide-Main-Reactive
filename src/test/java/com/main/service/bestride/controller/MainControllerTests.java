package com.main.service.bestride.controller;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.FileInputStream;
import java.io.IOException;

public class MainControllerTests {
    @Test
    public void testGetAllFares(){

        Flux<String> source = Flux.just("John", "Monica", "Mark", "Cloe", "Frank",
                        "Casper", "Olivia", "Emily", "Cate")
                .filter(name -> name.length() == 4)
                .map(String::toUpperCase);


        StepVerifier.create(source)
                .expectNext("JOHN")
                .expectNextMatches(name -> name.startsWith("MA"))
                .expectNext("CLOE", "CATE")
                .expectComplete()
                .verify();

    }

    public void getFluxData(){

        Flux.just("file1.txt", "nonexistent.txt", "file2.txt")
                .flatMap(filename -> Mono.fromCallable(() -> {
                    // Simulate file processing
                    if (filename.equals("nonexistent.txt")) {
                        throw new IOException("File not found: " + filename);
                    }
                    return new FileInputStream(filename); // Placeholder for actual processing
                }))
                .doOnNext(fileInputStream -> System.out.println("Processing file: " + fileInputStream))
                .onErrorContinue(IOException.class, (ex, filename) -> System.err.println("Error" +
                        " processing file " + filename  + ex.getMessage()))
                .subscribe(
                        result -> System.out.println("File processed successfully: " + result),
                        error -> System.err.println("Unexpected error in the stream: " +
                                error.getMessage()),
                        () -> System.out.println("Processing complete")
                );
    }

    public void getMonoData(){
        // Create a Mono that emits a single String value and provide fallback value incase of error
        Mono<String> dataMonoWithError = Mono.just("data")
                .flatMap(s -> {
                    if (s.equals("data")) {
                        return Mono.error(new RuntimeException("Simulated error!"));
                    }
                    return Mono.just("Processed " + s); })
                .onErrorReturn( "fallback value");

        dataMonoWithError.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error.getMessage()) // This won't be called in this case
        );
        //Output is “Received Fallback value”

    }
}
