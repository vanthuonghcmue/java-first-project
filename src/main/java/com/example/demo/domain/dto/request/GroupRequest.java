package com.example.demo.domain.dto.request;

import com.example.demo.markerinterface.GroupDto;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class GroupRequest implements GroupDto {
    private Long id;

    @NotBlank(message = "The name is required")
    private String name;

    private String description;

    @JsonAlias({"userIds", "user_ids"})
    private List<Long> userIds;
}
