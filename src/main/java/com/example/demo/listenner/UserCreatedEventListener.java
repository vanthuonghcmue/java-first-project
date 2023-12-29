package com.example.demo.listenner;

import com.example.demo.domain.entity.UserEntity;
import com.example.demo.event.UserCreatedEvent;
import com.example.demo.kafka.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class UserCreatedEventListener {

    private final KafkaSender kafkaSender;

    @Autowired
    public UserCreatedEventListener(KafkaSender kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    @TransactionalEventListener
    public void onUserCreated(UserCreatedEvent event) {
        UserEntity userEntity = event.getUser();
        kafkaSender.sendMessage(userEntity, "created_users");
    }
}
