package com.Auth.service;

import java.util.List;

import com.Auth.dto.AuthResponse;
import com.Auth.dto.GoogleAuthRequest;
import com.Auth.models.Usuario;

public interface UsuarioService {

    List<Usuario> listarUsuariosActivos();

    Usuario registrarOLoginConGoogle(String email, String nombre, String googleId, String fotoUrl);

    String generarToken(String email);

    AuthResponse loginConGoogle(GoogleAuthRequest request);
}
