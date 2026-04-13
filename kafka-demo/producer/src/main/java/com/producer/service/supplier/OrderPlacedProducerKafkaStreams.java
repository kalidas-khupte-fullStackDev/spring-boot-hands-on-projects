package com.producer.service.supplier;

import com.producer.service.dto.RiderLocation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

@Configuration
public class OrderPlacedProducerKafkaStreams {

    @Bean
    public Supplier<RiderLocation> sendRiderLocation() {
        return () -> {
            // This logic runs every time the Supplier is called (default is every 1 second)
            String uniqueRiderId = "rider-" + UUID.randomUUID().toString().substring(0, 8);
            RiderLocation location = new RiderLocation(uniqueRiderId, ThreadLocalRandom.current().nextDouble(18.0, 20.0), // Random Lat
                    ThreadLocalRandom.current().nextDouble(72.0, 74.0)  // Random Long
            );
            System.out.println("Producing: " + location.getRiderId());
            return location;
        };
    }

    @Bean
    public Supplier<Message<String>> sendRiderDeliveryStatus() {
        return () -> {
            // This logic runs every time the Supplier is called (default is every 1 second)
            String uniqueRiderId = "rider-" + UUID.randomUUID().toString().substring(0, 8);
            Random random = new Random(20);
            String riderStatus = random.nextBoolean() ? "Ride started" : "Ride Completed";
            System.out.println("Producing Ride status: " + riderStatus);

            return MessageBuilder.withPayload(uniqueRiderId + ":" + riderStatus).
                    setHeader(KafkaHeaders.KEY, uniqueRiderId.getBytes())
                    .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.TEXT_PLAIN_VALUE).build();
        };
    }
}
