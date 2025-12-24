package com.Auth.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PowerResponse {

    private String equipo;
    private String power;
    private LocalDateTime activoDesde;
    private LocalDateTime activoHasta;

}
