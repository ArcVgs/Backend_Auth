package com.Auth.service;

import com.Auth.models.Usuario;

public interface PuntoGPSService {

    void agregarPunto(Usuario usuario, Double lat, Double lng);

}
