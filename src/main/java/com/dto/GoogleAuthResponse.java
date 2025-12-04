package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoogleAuthResponse {

    private String mensaje;
    private Long id;
    private Integer estado;

}
