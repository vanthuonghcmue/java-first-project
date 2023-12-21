package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import java.util.List;

public interface IUserService {
    UserDTO save(UserDTO userDTO);
    List<UserDTO> all();
}
