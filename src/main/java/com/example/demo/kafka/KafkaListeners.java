package com.example.demo.kafka;

import com.example.demo.domain.entity.UserEntity;
import com.example.demo.service.Email.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class KafkaListeners {
    private final EmailService emailService;

    @Autowired
    public KafkaListeners(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "created_users")
    void listener(String message) throws JsonProcessingException {
        System.out.println(message);
        ObjectMapper objectMapper = new ObjectMapper();

        // Convert JSON string to UserEntity
        UserEntity userEntity = objectMapper.readValue(message, UserEntity.class);
        emailService.sendEmail(
                userEntity.getEmail(),
                "Well come to presentation app",
                "Hi " + userEntity.getUsername() + ", Well come!"
        );
    }
}
