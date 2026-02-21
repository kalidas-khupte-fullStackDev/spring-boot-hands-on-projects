package com.example.consumer.service.communication.interfaceservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HttpServiceClientProvider {

    private final HttpServiceClientInterface httpServiceClientInterfaceService;

    public String getRestClientBasedProviderServiceInfo() {
        System.out.println("Sent Packet reqeust from consumer service via Http Service Client Interface wrapped with REST Client");
        String res = "Sent Packet reqeust from consumer service via Http Service Client Interface wrapped with REST Client";
        String providerRes = httpServiceClientInterfaceService.getInstanceInfo();
        return String.join(" ", res , providerRes);
    }
    public String getWebClientBasedProviderServiceInfo() {
        System.out.println("Sent Packet reqeust from consumer service via Http Service Client Interface wrapped with WebClient");
        String res = "Sent Packet reqeust from consumer service via Http Service Client Interface wrapped with WebClient";
        String providerRes = httpServiceClientInterfaceService.getInstanceInfo();
        return String.join(" ", res , providerRes);
    }
}
