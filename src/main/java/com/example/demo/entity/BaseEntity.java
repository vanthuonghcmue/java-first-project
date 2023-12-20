package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;


@MappedSuperclass
public abstract class BaseEntity {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

}
