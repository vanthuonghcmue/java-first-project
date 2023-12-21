package com.example.demo.converter;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {
    @Autowired
    private ModelMapper mapper;

    public UserEntity toEntity(UserDTO userDTO) {
        return mapper.map(userDTO, UserEntity.class);
    }

    public UserDTO toDTO(UserEntity userEntity) {
        return mapper.map(userEntity, UserDTO.class);
    }

    public List<UserDTO> toDTO(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
