package com.example.demo.mapper.impl;

import com.example.demo.markerinterface.UserDto;
import com.example.demo.domain.dto.request.RegisterUserRequest;
import com.example.demo.domain.dto.resource.UserResource;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.mapper.IMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper implements IMapper<UserEntity, UserDto, UserResource> {
    @Autowired
    private ModelMapper mapper;

    public UserEntity toEntity(RegisterUserRequest registerUserRequest) {
        return mapper.map(registerUserRequest, UserEntity.class);
    }

    public RegisterUserRequest toDto(UserEntity userEntity) {
        return mapper.map(userEntity, RegisterUserRequest.class);
    }

    public List<RegisterUserRequest> toDto(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
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
