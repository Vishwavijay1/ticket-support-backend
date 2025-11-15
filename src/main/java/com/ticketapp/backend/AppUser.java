package com.ticketapp.backend;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "app_users") // "User" is a reserved SQL word, so we name the table "users"
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String role; // e.g., "USER", "ADMIN"
}