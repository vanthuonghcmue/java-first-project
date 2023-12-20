package com.example.demo.converter;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserEntity toEntity(UserDTO userDTO) {
        UserEntity entity = new UserEntity();
        entity.setName(userDTO.getName());
        entity.setUsername(userDTO.getUsername());
        entity.setPassword(userDTO.getPassword());
        return entity;
    }

    public UserDTO toDTO(UserEntity userEntity) {
        UserDTO dto = new UserDTO();
        dto.setName(userEntity.getName());
        dto.setUsername(userEntity.getUsername());
        return dto;
    }
}
