package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class GoogleLoginAuthResponse {
    private String mensaje;
    private Long id;
    private Integer estado;
    private String token;
}
