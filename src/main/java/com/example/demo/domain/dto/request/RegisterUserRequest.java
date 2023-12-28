package com.example.demo.domain.dto.request;

import com.example.demo.annotation.UniqueUsername;
import com.example.demo.markerinterface.UserDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest implements UserDto {
//    @NotEmpty(message = "NotEmpty")
    @NotBlank(message = "The name is required")
    private String name;

    @NotBlank(message = "The username is required")
    @UniqueUsername
    private String username;

    @NotEmpty(message = "The password is required")
    @Size(min=8, message = "password not less than 8 character")
    private String password;

    @Email
    @NotBlank(message = "The email is required")
    private String email;
}
