package com.example.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
@Component
public class KafkaSender {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    KafkaSender(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Object message, String topicName) {
        kafkaTemplate.send(topicName, message);
    }


}
