package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String name;

    private String username;

    @JsonIgnore
    private String password;
}
