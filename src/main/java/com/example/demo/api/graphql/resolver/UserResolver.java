package com.example.demo.api.graphql.resolver;

import com.example.demo.domain.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserResolver {
    private final UserRepository userRepository;

    public UserResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryMapping
    public List<UserEntity> users(){
        return userRepository.findAll();
    }
}
