package com.service;

import java.util.List;

import com.models.Usuario;

public interface UsuarioService {

    List<Usuario> listarUsuariosActivos();

    Usuario registrarOLoginConGoogle(String email, String nombre, String googleId, String fotoUrl);
}
