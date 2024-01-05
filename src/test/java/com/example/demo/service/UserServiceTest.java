package com.example.demo.service;

import com.example.demo.domain.dto.request.RegisterUserRequest;
import com.example.demo.domain.dto.resource.UserResource;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.exception.customexception.ResourceNotFoundException;
import com.example.demo.mapper.impl.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @Mock
    RegisterUserRequest registerUserRequest;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserMapper userMapper;

    @Mock
    ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    UserService userService;

    @Test
    void testCreateUserSuccessful(){
        //   1. create mock data
        RegisterUserRequest request =  RegisterUserRequest.builder()
                .email("dvthuong@gmail.com")
                .name("thuong")
                .username("dvthuong")
                .password("12345678x@X")
                .build();
        UserEntity expectedEntity = UserEntity.builder()
                .email("dvthuong@gmail.com")
                .name("thuong")
                .username("dvthuong")
                .password("12345678x@X")
                .build();
        UserResource expectedResource = UserResource.builder()
                .email("dvthuong@gmail.com")
                .name("thuong")
                .username("dvthuong")
                .build();

        // 2. define behavior of Repository
        when(userMapper.mapFrom(request)).thenReturn(expectedEntity);
        when(passwordEncoder.encode(anyString())).thenReturn("12345678x@X");
        when(userRepository.save(expectedEntity)).thenReturn(expectedEntity);
        when(userMapper.mapTo(expectedEntity)).thenReturn(expectedResource);
        // 3. call service method
        UserResource actualResource = userService.save(request);

        // 4. assert the result
        assertEquals(expectedResource, actualResource);
        verify(passwordEncoder).encode(expectedEntity.getPassword());
        verify(userRepository).save(expectedEntity);
        verify(userMapper).mapTo(expectedEntity);

        // 4.1 ensure repository is called
        verify(userRepository).save(expectedEntity);
    }

    @Test
    void testGetUserByIdSuccessful(){
        Long userId = 1L;
        UserEntity expectedEntity = UserEntity.builder()
                .email("dvthuong@gmail.com")
                .name("thuong")
                .username("dvthuong")
                .password("12345678x@X")
                .build();

        UserResource expectedResource = UserResource.builder()
                .email("dvthuong@gmail.com")
                .name("thuong")
                .username("dvthuong")
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedEntity));
        when(userMapper.mapTo(expectedEntity)).thenReturn(expectedResource);

        UserResource actualResource = userService.getById(userId);

        assertEquals(expectedResource, actualResource);
        verify(userRepository).findById(userId);
        verify(userMapper).mapTo(expectedEntity);

    }

    @Test
    void testGetUserByIdInvalid(){
        Long userId = 2L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getById(userId));
        verify(userRepository).findById(userId);
    }
}
