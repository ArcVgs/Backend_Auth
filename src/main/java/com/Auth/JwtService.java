package com.Auth;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.models.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final Key key = Keys.hmacShaKeyFor("secretKey110011223344556677889900".getBytes());

    public String generateToken(Usuario usuario) {
        return Jwts.builder().setSubject(usuario.getEmail())
                .claim("id", usuario.getId())
                .claim("estado", usuario.getEstado())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

}
