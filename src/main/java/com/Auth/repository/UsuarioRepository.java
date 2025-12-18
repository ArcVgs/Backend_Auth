package com.Auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Auth.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
