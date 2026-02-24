package com.ecommerce.order.serviceclient.config;

import com.ecommerce.order.serviceclient.exchange.UserServiceClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Optional;

@Configuration
public class UserServiceClientBeanProvider {

    private static final String USER_SERVICE_URL = "http://user-service";

    @Bean
    @LoadBalanced
    public UserServiceClient userServiceInterface(@LoadBalanced RestClient.Builder restClientBuilder) {
        RestClient restClient = restClientBuilder
                .baseUrl(USER_SERVICE_URL)
                .defaultStatusHandler(HttpStatusCode::is4xxClientError,
                        ((request, response) -> Optional.empty()))
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(adapter)
                .build();
        return factory.createClient(UserServiceClient.class);
    }
}
