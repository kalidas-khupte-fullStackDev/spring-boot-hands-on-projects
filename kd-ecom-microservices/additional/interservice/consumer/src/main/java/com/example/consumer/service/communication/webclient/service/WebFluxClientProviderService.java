package com.example.consumer.service.communication.webclient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WebFluxClientProviderService {

    private final WebClient webClient;

    public String getProviderServiceInfo() {
        System.out.println("Sent Packet reqeust from consumer service viaWeb Flux Client");
        String res = "Sent Packet reqeust from consumer service viaWeb Flux Client";
        Mono<String> providerRes = webClient.get().uri("/ping-service").retrieve().bodyToMono(String.class);
        return String.join(" ", res , providerRes.block());
    }
}
