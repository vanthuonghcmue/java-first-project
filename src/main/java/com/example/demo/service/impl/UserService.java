package com.example.demo.service.impl;

import com.example.demo.domain.dto.request.RegisterUserRequest;
import com.example.demo.domain.dto.request.UserRequest;
import com.example.demo.domain.dto.resource.UserResource;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.exception.CustomException.ResourceNotFoundException;
import com.example.demo.mapper.impl.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IUserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }
    @Transactional
    public UserResource save(RegisterUserRequest registerUserRequest) {
        UserEntity userEntity = userMapper.mapFrom(registerUserRequest);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return this.userMapper.mapTo(userRepository.save(userEntity));
    }

    @Transactional
    public UserResource save(UserRequest userRequest) {
        UserEntity userEntity = userMapper.mapFrom(userRequest);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return this.userMapper.mapTo(userRepository.save(userEntity));
    }

    @Override
    public List<UserResource> all() {
        List<UserEntity> userEntities =userRepository.findAll();
        return userEntities.stream().map(userMapper::mapTo).collect(Collectors.toList());
    }

    @Override
    public UserResource delete(Long id) {
        UserEntity deletedUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.deleteById(id);
        return userMapper.mapTo(deletedUser);
    }

    @Override
    public UserResource partialUpdate(Long id, UserRequest userRequest) {
        UserEntity userEntity =  userRepository.findById(id).map(
            existingUser -> {
                Optional.ofNullable(userRequest.getName()).ifPresent(existingUser::setName);
                Optional.ofNullable(userRequest.getEmail()).ifPresent(existingUser::setEmail);
                Optional.ofNullable(userRequest.getUsername()).ifPresent(existingUser::setUsername);
                return userRepository.save(existingUser);
            }
        ).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.mapTo(userEntity);
    }


    public Boolean exists(String username){
        Optional<UserEntity> existingUser = userRepository.findByUsername(username);
        return existingUser.isPresent();
    }

    @Override
    public UserResource getById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.mapTo(userEntity);
    }


}
