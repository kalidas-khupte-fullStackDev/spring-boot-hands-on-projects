//package com.consumer.service.config;
//
//import com.consumer.service.dto.RiderLocation;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.converter.JacksonJsonMessageConverter;
//import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.kafka.support.converter.RecordMessageConverter;
//import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaConsumerConfig {
//
//    @Bean
//    public ConsumerFactory<String, RiderLocation> consumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
//
//        // ✅ Modern replacement for JsonDeserializer
//        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JacksonJsonDeserializer.class);
//        props.put(JacksonJsonDeserializer.TRUSTED_PACKAGES, "*");
//        props.put(JacksonJsonDeserializer.VALUE_DEFAULT_TYPE, RiderLocation.class.getName());
//        props.put(JacksonJsonDeserializer.USE_TYPE_INFO_HEADERS, false);
//
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
//
//    @Bean
//    public RecordMessageConverter messageConverter() {
//        // ✅ Modern replacement for JsonMessageConverter
//        return new JacksonJsonMessageConverter();
//    }
//}
