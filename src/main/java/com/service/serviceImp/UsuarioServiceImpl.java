package com.service.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.Usuario;
import com.repository.UsuarioRepository;
import com.service.UsuarioService;
import com.repository.UsuarioRepositoryCustom;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepositoryCustom UsuarioRepositoryCustom;
    @Autowired
    private UsuarioRepository UsuarioRepository;

    @Override
    public Usuario registrarOLoginConGoogle(String email, String nombre, String googleId, String fotoUrl) {
        // Buscamos por Email

        Usuario usuario = UsuarioRepository.findByEmail(email);

        if (usuario != null) {
            // Ya existe el usuario
            usuario.setNombre(usuario.getNombre());
            usuario.setGoogleId(usuario.getGoogleId());
            usuario.setFotoUrl(usuario.getFotoUrl());
            return UsuarioRepository.save(usuario);
        }

        // No existe el usuario
        usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setGoogleId(googleId);
        usuario.setFotoUrl(fotoUrl);
        return UsuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarUsuariosActivos() {
        return UsuarioRepositoryCustom.listarUsuariosActivos();
    }

}
