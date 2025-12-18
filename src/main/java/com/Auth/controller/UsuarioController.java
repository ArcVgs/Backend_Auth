package com.Auth.controller;

import com.Auth.dto.GoogleAuthRequest;
import com.Auth.dto.GoogleAuthResponse;
import com.Auth.enums.EstadosUsuario;
import com.Auth.models.Usuario;
import com.Auth.repository.UsuarioRepository;
import com.Auth.service.UsuarioService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/registrar")
    public Usuario registrarOLoginConGoogle(@RequestBody GoogleAuthRequest request) {
        return usuarioService.registrarOLoginConGoogle(request.getEmail(), request.getNombre(), request.getGoogleId(),
                request.getFotoUrl());
    }

    @PutMapping("/{id}/actualizar")
    public ResponseEntity<GoogleAuthResponse> actualizarUsuario(@PathVariable Long id, @RequestParam Integer estado) {

        if (!EstadosUsuario.esActivo(estado)) {
            return ResponseEntity.badRequest()
                    .body(new GoogleAuthResponse("Es estado debe ser 0 (Inactivo) o 1 (Activo)", null, null));
        }

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(new GoogleAuthResponse("Usuario no encontrado", null, null));
        }

        Usuario usuario = optionalUsuario.get();
        usuario.setEstado(estado);
        Usuario actualizado = usuarioRepository.save(usuario);
        GoogleAuthResponse response = new GoogleAuthResponse(
                "Usuario Actualizado",
                actualizado.getId(),
                actualizado.getEstado());
        return ResponseEntity.ok(response);
    }

}
