package com.consumer.service.listener;

import com.consumer.service.dto.RiderLocation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class OrderPlacedConsumerStreams {

    @Bean
    public Consumer<RiderLocation> getRiderLocation() {
        return riderLocation -> {
            System.out.println("Received : " + riderLocation.getRiderId() + "@: " + riderLocation.getLongitude() + ", " + riderLocation.getLatitude());
        };
    }

}
