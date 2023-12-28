package com.example.demo.service;

import com.example.demo.domain.dto.request.RegisterUserRequest;
import com.example.demo.domain.dto.request.UserRequest;
import com.example.demo.domain.dto.resource.UserResource;

import java.util.List;

public interface IUserService {
    UserResource save(RegisterUserRequest registerUserRequest);
    UserResource partialUpdate(Long id, UserRequest userRequest);
    Boolean exists(String username);
    UserResource getById(Long id);
    List<UserResource> all();
    UserResource delete(Long id);
}

