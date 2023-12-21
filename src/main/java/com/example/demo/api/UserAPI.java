package com.example.demo.api;

import com.example.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.impl.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserAPI {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<UserDTO>();
        users = userService.all();
        return users;
    }
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable String id) {

        return null;
    }

    @PostMapping("")
    public UserDTO createUser(@RequestBody UserDTO user) {
        return userService.save(user);
    }
}
