package com.example.demo.domain.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResource {
    private Long id;
    private String name;
    private String username;
    private String email;
}
