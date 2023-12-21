package com.example.demo.service.impl;

import com.example.demo.converter.UserConverter;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO save(UserDTO userDTO) {
        UserEntity userEntity = userConverter.toEntity(userDTO);
        // Save UserEntity
        return userConverter.toDTO(userRepository.save(userEntity));
    }

    @Override
    public List<UserDTO> all() {
        return userConverter.toDTO(userRepository.findAll());
    }
}
