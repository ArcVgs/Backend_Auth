package com.Auth.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Auth.models.Usuario;
import com.Auth.service.PuntoGPSService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recorridos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Permitir CORS para todas las fuentes (ajusta según tus necesidades)
public class PuntoGPSController {

    private final PuntoGPSService puntoGPSService;

    @PostMapping("/punto")
    public ResponseEntity<Void> agregarPunto(
            @RequestParam Double lat,
            @RequestParam Double lng,
            @AuthenticationPrincipal Usuario usuario) {

        puntoGPSService.agregarPunto(usuario, lat, lng);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarPunto(@RequestBody Map<String, Object> payload) {
        // Cambiado de 'latitude' a 'lat' y 'longitude' a 'lng'
        System.out.println("Latitud recibida: " + payload.get("lat"));
        System.out.println("Longitud recibida: " + payload.get("lng"));
        System.out.println("Usuario ID recibido: " + payload.get("usuarioId"));

        return ResponseEntity.ok().build();
    }
}