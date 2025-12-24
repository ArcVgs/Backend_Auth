package com.Auth.dto;

import com.Auth.enums.TipoPower;

import lombok.Data;

@Data
public class ActivarPowerRequest {

    private Long equipoId;
    private TipoPower tipoPower;
    private int duracionMinutos;

}
