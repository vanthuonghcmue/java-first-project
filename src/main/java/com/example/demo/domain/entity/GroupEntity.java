package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="groups")
public class GroupEntity extends BaseEntity{
    @Getter
    @Column(nullable=false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany(mappedBy = "groups", cascade = {CascadeType.ALL})
    private Set<UserEntity> users = new HashSet<>();
}
