package com.consumer.service.listener;

import com.consumer.service.dto.RiderLocation;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderPlacedConsumer {

    @KafkaListener(topics = "order-placed", groupId = "order-msg-related-group")
    public String orderPlacedNotifierMsg(String message){
        System.out.println("Message from Producer on order-placed topic (Msg): " + message);
        return message;
    }

    @KafkaListener(topics = "order-placed-data", groupId = "order-data-related-group")
    public RiderLocation orderPlacedNotifierData(RiderLocation riderLocationDetails){
        System.out.println("Message from Producer on order-placed topic (Data): " + riderLocationDetails);
        return riderLocationDetails;
    }
}
