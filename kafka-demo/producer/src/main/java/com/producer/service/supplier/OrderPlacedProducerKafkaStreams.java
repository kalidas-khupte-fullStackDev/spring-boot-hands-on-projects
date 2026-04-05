package com.producer.service.supplier;

import com.producer.service.dto.RiderLocation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class OrderPlacedProducerKafkaStreams {

    @Bean
    public Supplier<RiderLocation> sendRiderLocation(){
        return () -> {
            RiderLocation location = new RiderLocation("rider123", 86.23, 96.25);
            System.out.println("Sending: " + location.getRiderId());
            return location;
        };
    }
}
