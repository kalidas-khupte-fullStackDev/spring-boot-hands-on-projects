package com.producer.service.controller;

import com.producer.service.dto.RiderLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderPlacedProducer {

    private KafkaTemplate<String, String> plainTextMsgKafkaTemplate;
    private KafkaTemplate<String, RiderLocation> riderLocationKafkaTemplate;

    @Autowired
    public void setPlainTextMsgKafkaTemplate(KafkaTemplate<String, String> plainTextMsgKafkaTemplate) {
        this.plainTextMsgKafkaTemplate = plainTextMsgKafkaTemplate;
    }

    @Autowired
    public void setRiderLocationKafkaTemplate(KafkaTemplate<String, RiderLocation> riderLocationKafkaTemplate) {
        this.riderLocationKafkaTemplate = riderLocationKafkaTemplate;
    }

//    @PostMapping("/notify")
//    public ResponseEntity<?> notifyOnOrderPlace(@RequestParam String message){
//        riderLocationKafkaTemplate.send("order-placed", message);
//        return ResponseEntity.ok().body("Message delivered to consumer Successfully");
//    }

    @PostMapping("/send/location")
    public ResponseEntity<?> notifyOnOrderPlace(@RequestParam String message){
        plainTextMsgKafkaTemplate.send("order-placed", message);
        System.out.println("Message: "+ message);
        RiderLocation riderLocation = new RiderLocation("123", 34.78, 78.23);
        riderLocationKafkaTemplate.send("order-placed-data", riderLocation);
        return ResponseEntity.ok().body("Message delivered to consumer Successfully");
    }

//    @PostMapping("/notify-new")
//    public ResponseEntity<?> notifyOnOrderPlaceNew(@RequestParam String message){
//        riderLocationKafkaTemplate.send("new", message);
//        return ResponseEntity.ok().body("Message delivered to consumer Successfully");
//    }
}
