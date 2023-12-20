package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Setter
@Entity
@Table(name="users")
public class UserEntity extends BaseEntity{
    @Getter
    @Column
    private String name;

    @Getter
    @Column
    private String username;

    @Column
    private String password;
}
