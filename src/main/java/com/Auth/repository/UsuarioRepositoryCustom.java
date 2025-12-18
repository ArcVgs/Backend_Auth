package com.Auth.repository;

import java.util.List;

import com.Auth.models.Usuario;

public interface UsuarioRepositoryCustom {

    public List<Usuario> listarUsuariosActivos();
}
