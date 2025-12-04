package com.dto;

import lombok.Data;

@Data
public class GoogleAuthRequest {

    private String email;
    private String nombre;
    private String googleId;
    private String fotoUrl;

}
