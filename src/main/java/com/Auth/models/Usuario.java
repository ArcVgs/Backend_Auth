package com.Auth.models;

import com.Auth.enums.RolUsuario;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String email;

    @Column(name = "google_id", unique = true)
    private String googleId;

    @Column(name = "foto_url")
    private String fotoUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private RolUsuario rol;

    @Column(nullable = false)
    private boolean enabled;

}
