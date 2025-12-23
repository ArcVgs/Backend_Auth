package com.Auth.dto;

import com.Auth.models.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private Usuario usuario;
}
