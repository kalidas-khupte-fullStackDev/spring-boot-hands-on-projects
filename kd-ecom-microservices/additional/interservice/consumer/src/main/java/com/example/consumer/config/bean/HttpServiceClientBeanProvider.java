package com.example.consumer.config.bean;

import com.example.consumer.service.communication.interfaceservice.service.HttpServiceClientInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.client.support.RestTemplateAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class HttpServiceClientBeanProvider {

    private static final String baseURL = "http://provider-service";

//    @Bean
//    public HttpServiceClientInterface getRestTemplateImpl(RestTemplate restTemplate){
//        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(baseURL));
//        RestTemplateAdapter adapter = RestTemplateAdapter.create(restTemplate);
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
//        return factory.createClient(HttpServiceClientInterface.class);
//    }

//    @Bean
//    public HttpServiceClientInterface getRestClientImpl(RestClient restClient){
//        RestClientAdapter adapter = RestClientAdapter.create(restClient);
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
//        return factory.createClient(HttpServiceClientInterface.class);
//    }

    @Bean
    public HttpServiceClientInterface getWebClientImpl(WebClient webClient){
        WebClientAdapter adapter = WebClientAdapter.create(webClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(HttpServiceClientInterface.class);
    }
}
