package com.example.demo.api;

import com.example.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.implement.UserService;

@RestController
@RequestMapping("/api/users")
public class UserAPI {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String getAllUser() {
        return "hello";
    }
    @GetMapping("/1")
    public String getAllUser1() {
        return "1";
    }

    @PostMapping("")
    public UserDTO createUser(@RequestBody UserDTO user) {
        return user;
    }
}
