package com.factoryflow.auth.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(
    		strategy = GenerationType.SEQUENCE,
    		generator = "sequence")
    @SequenceGenerator(
    		name = "sequence",
    		allocationSize = 1)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String address;

    private String role;

    private boolean status;
    
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void createdTime() {
        this.createdAt = LocalDateTime.now();
    }
}