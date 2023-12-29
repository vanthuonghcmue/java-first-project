package com.example.demo.api.rest;

import com.example.demo.domain.dto.request.RegisterUserRequest;
import com.example.demo.domain.dto.request.UserRequest;
import com.example.demo.domain.dto.resource.UserResource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.impl.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserApi {
    private final UserService userService;

    @Autowired
    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseWrapper<UserResource>> getAllUsers() {
        List<UserResource> users = userService.all();
        ResponseWrapper<UserResource> response = new ResponseWrapper<>();
        response.setData(users);
        response.setMessage("Get users successful");
        response.setCode("get_users_successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<UserResource>> getUserById(@PathVariable Long id) {
        UserResource userResource = userService.getById(id);
        ResponseWrapper<UserResource> response = new ResponseWrapper<>();
        response.setData(userResource);
        response.setMessage("Get user successful");
        response.setCode("get_user_successful");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("")
    public ResponseEntity<ResponseWrapper<UserResource>> createUser(
            @Valid @RequestBody RegisterUserRequest registerUserRequest
    ){
        UserResource userResource = userService.save(registerUserRequest);
        ResponseWrapper<UserResource> response = new ResponseWrapper<>();
        response.setData(userResource);
        response.setMessage("Create user successful");
        response.setCode("create_user_successful");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<UserResource>> deleteUser(@PathVariable Long id) {
        UserResource userResource = userService.delete(id);
        ResponseWrapper<UserResource> response = new ResponseWrapper<>();
        response.setData(userResource);
        response.setMessage("Delete user successful");
        response.setCode("delete_user_successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseWrapper<UserResource>> updatePartialUser(
            @PathVariable Long id,
            @RequestBody UserRequest userRequest
    ) {
        UserResource userResource = userService.partialUpdate(id, userRequest);
        ResponseWrapper<UserResource> response = new ResponseWrapper<>();
        response.setData(userResource);
        response.setMessage("Update user successful");
        response.setCode("update_user_successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<UserResource>> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequest userRequest
    ) {
        userRequest.setId(id);
        UserResource userResource = userService.save(userRequest);
        ResponseWrapper<UserResource> response = new ResponseWrapper<>();
        response.setData(userResource);
        response.setMessage("Update user successful");
        response.setCode("update_user_successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
