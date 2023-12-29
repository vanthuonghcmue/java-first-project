package com.example.demo.mapper.impl;

import com.example.demo.markerinterface.UserDto;
import com.example.demo.domain.dto.resource.UserResource;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.mapper.IMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IMapper<UserEntity, UserDto, UserResource> {
    private final ModelMapper mapper;

    @Autowired
    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserResource mapTo(UserEntity userEntity) {
        return mapper.map(userEntity, UserResource.class);
    }

    @Override
    public UserEntity mapFrom(UserDto registerUserRequest) {
        return mapper.map(registerUserRequest, UserEntity.class);
    }
}
