package com.ecommerce.notification.event;

import com.ecommerce.notification.payload.OrderCreatedEvent;
import com.ecommerce.notification.payload.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderEventConsumer {

    @RabbitListener(queues = "${spring.rabbitmq.queue.name}")
    public void handleOrderEvent(OrderCreatedEvent orderEvent){
        System.out.println("Received Order Event: " + orderEvent);

        long orderId = orderEvent.getOrderId();
        OrderStatus orderStatus = orderEvent.getStatus();

        System.out.println("Order ID: " + orderId);
        System.out.println("Order Status: " + orderStatus);

        // Update Database
        // Send Notification
        // Send Emails
        // Generate Invoice
        // Send Seller Notification

    }
}
