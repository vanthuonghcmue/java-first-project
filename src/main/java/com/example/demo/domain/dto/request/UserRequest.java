package com.example.demo.domain.dto.request;

import com.example.demo.annotation.UniqueUsername;
import com.example.demo.markerinterface.OnCreate;
import com.example.demo.markerinterface.UserDto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest implements UserDto {
    @Null(groups = OnCreate.class)
    private Long id;

//    @Null(groups = OnUpdate.class)
//    @NotNull(groups = OnCreate.class, message = "The name not null")
    @NotBlank(message = "The name is required")
    private String name;

    @NotNull(groups = OnCreate.class, message = "The username not null")
//    @NotBlank(message = "The username is required")
    @UniqueUsername
    private String username;

    @NotNull(groups = OnCreate.class, message = "The password not null")
    @Size(min=8, message = "password not less than 8 character")
    private String password;

    @Email
    @NotBlank(message = "The email is required")
    private String email;
}
