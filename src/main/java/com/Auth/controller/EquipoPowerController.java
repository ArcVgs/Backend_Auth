package com.Auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Auth.dto.ActivarPowerRequest;
import com.Auth.dto.PowerResponse;
import com.Auth.service.EquipoPowerService;

@RestController
@RequestMapping("/equipo-power")
public class EquipoPowerController {

    @Autowired
    private EquipoPowerService equipoPowerService;

    @PostMapping("/activar")
    public ResponseEntity<PowerResponse> activarPower(@RequestBody ActivarPowerRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        Long usuarioId = Long.valueOf(userDetails.getUsername());
        return ResponseEntity.ok(equipoPowerService.activarPower(usuarioId, request));
    }

}
