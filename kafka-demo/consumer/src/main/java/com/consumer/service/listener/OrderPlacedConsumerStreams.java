package com.consumer.service.listener;

import com.consumer.service.dto.RiderLocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
public class OrderPlacedConsumerStreams {

    @Bean
    public Consumer<RiderLocation> receiveRiderLocation() {
        return riderLocation -> {
            System.out.println("Received location: " + riderLocation.getLatitude());
        };
    }

//    @Bean
//    public Consumer<Message<RiderLocation>> receiveRiderLocation() {
//        return riderLocation -> {
//            // Spring Cloud Stream automatically converts the JSON string
//            // from the topic into your RiderLocation object here.
//            RiderLocation location = riderLocation.getPayload();
//
//            System.out.println("------------------------------------");
//            System.out.println("Successfully Cast Message to Object!");
//            System.out.println("Rider ID: " + location.getRiderId());
//            System.out.println("Latitude: " + location.getLatitude());
//            System.out.println("Longitude: " + location.getLongitude());
//            System.out.println("------------------------------------");
//        };
//    }

//    @Bean
//    public Consumer<Message<byte[]>> receiveRiderLocation() {
//        return message -> {
//            byte[] payload = message.getPayload();
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            try {
//                // This manually forces the byte[57] into your object
//                RiderLocation location = objectMapper.readValue(payload, RiderLocation.class);
//
//                System.out.println("Success! Received Rider: " + location.getRiderId());
//                System.out.println("Lat: " + location.getLatitude() + " Long: " + location.getLongitude());
//
//            } catch (Exception e) {
//                // THIS WILL PRINT THE ACTUAL REASON IT IS FAILING
//                // (e.g. "Unrecognized field", "Missing constructor", etc.)
//                System.err.println("JSON Conversion Error: " + e.getMessage());
//                System.err.println("Raw Payload was: " + new String(payload));
//            }
//        };
//    }

//    @Bean
//    public Consumer<String> getRiderLocation() {
//        return payload -> {
//            System.out.println("Raw Data Received: " + payload);
//        };
//    }

}
