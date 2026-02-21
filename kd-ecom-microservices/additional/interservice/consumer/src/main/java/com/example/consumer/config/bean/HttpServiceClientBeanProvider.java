package com.example.consumer.config.bean;

import com.example.consumer.service.communication.interfaceservice.service.HttpServiceClientInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpServiceClientBeanProvider {

    @Bean
    public HttpServiceClientInterface getRestClientImpl(RestClient restClient){
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(HttpServiceClientInterface.class);
    }

//    @Bean
//    public HttpServiceClientInterface getWebClientImpl(WebClient webClient){
//        WebClientAdapter adapter = WebClientAdapter.create(webClient);
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
//        return factory.createClient(HttpServiceClientInterface.class);
//    }
}
