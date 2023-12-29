package com.example.demo.domain.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupResource {
    private Long id;
    private String name;
    private String description;
    private Set<UserResource> users;
}
