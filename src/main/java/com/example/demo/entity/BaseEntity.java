package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Date;


@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    public Long getId() {
        return id;
    }
}
