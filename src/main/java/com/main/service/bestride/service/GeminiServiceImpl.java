package com.main.service.bestride.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeminiServiceImpl implements GeminiService{

    @Autowired
    @Qualifier("gemini")
    private WebClient webClient;

    @Override
    public Mono<Integer> getPincode(String destination) {
        {
            String updatedPrompt = "Tell me the pincode of " + destination + ". \n" +
                    "Just give the pincode without any extra text. \n" +
                    "There shouldnot be any text in the response apart from the pincode number";
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("contents", List.of(Map.of("parts",List.of(Map.of("text",updatedPrompt)))));

            System.out.println("GemSer: requestBody -"+requestBody );

            return webClient.post().header("x-goog-api-key",String.format("%s%s%s","AIzaSyCuuPX","-AAIi_4gsWHrX-nAIYMBbDNPl","uZ4"))
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .map(pincodeString -> {
                        System.out.println("GeminiService::pincodeString::"+pincodeString);
                        int indexStart = pincodeString.indexOf("500");
                        String trimPincode = pincodeString.substring(indexStart,indexStart+6);
                        int pincodeInt = Integer.parseInt(trimPincode);
                        System.out.println("GeminiService::pincodeInt::"+pincodeInt);
                        return pincodeInt;
                    });
        }
    }
}
