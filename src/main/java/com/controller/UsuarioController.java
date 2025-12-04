package com.controller;

import com.dto.GoogleAuthRequest;
import com.models.Usuario;
import com.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService UsuarioService;

    @PostMapping("/registrar")
    public Usuario registrarOLoginConGoogle(@RequestBody GoogleAuthRequest request) {
        return UsuarioService.registrarOLoginConGoogle(request.getEmail(), request.getNombre(), request.getGoogleId(),
                request.getFotoUrl());
    }

}
