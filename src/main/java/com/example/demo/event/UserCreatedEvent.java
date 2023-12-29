package com.example.demo.event;

import com.example.demo.domain.entity.UserEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserCreatedEvent extends ApplicationEvent {
    private final UserEntity user;

    public UserCreatedEvent(UserEntity user) {
        super(user);
        this.user = user;
    }

}
