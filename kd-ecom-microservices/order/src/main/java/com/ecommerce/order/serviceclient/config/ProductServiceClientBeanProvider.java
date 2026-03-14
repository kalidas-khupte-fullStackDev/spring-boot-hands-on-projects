package com.ecommerce.order.serviceclient.config;

import com.ecommerce.order.serviceclient.exchange.ProductServiceClient;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.propagation.Propagator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Objects;
import java.util.Optional;

@Configuration
public class ProductServiceClientBeanProvider {

    private static final String PRODUCT_SERVICE_URL = "http://product-service";

    @Autowired(required = false)
    private ObservationRegistry observationRegistry;

    @Autowired(required = false)
    private Tracer tracer;
    @Autowired(required = false)
    private Propagator propagator;

    @Bean
    public ProductServiceClient productServiceInterface(@LoadBalanced RestClient.Builder restClientBuilder) {
        assert createTracingInterceptor() != null;
        RestClient restClient = restClientBuilder
                .baseUrl(PRODUCT_SERVICE_URL)
                .defaultStatusHandler(HttpStatusCode::is4xxClientError,
                        ((request, response) -> Optional.empty()))
                .requestInterceptor(createTracingInterceptor())
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(adapter)
                .build();
        return factory.createClient(ProductServiceClient.class);
    }

    private ClientHttpRequestInterceptor createTracingInterceptor() {
        if (observationRegistry != null) {
            return ((request, body, execution) -> {
                if (tracer != null && propagator != null && tracer.currentSpan() != null) {
                    propagator.inject(Objects.requireNonNull(tracer.currentTraceContext().context()), request.getHeaders(), (carrier, key, value) -> {
                        assert carrier != null;
                        carrier.add(key, value);
                    });
                }
                return execution.execute(request, body);
            });
        }
        return null;
    }
}
