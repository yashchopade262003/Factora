package com.factoryflow.auth.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String address;

    private String role;

    private boolean status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void createdTime() {
        this.createdAt = LocalDateTime.now();
    }
}