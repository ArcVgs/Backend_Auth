package com.Auth.service;

import com.Auth.models.Recorrido;
import com.Auth.models.Usuario;

public interface RecorridoService {

    Recorrido iniciarRecorrido(Usuario usuario);

    Recorrido obtenerRecorridoActivo(Usuario usuario);

    Recorrido cerrarRecorrido(Long recorridoId, Usuario usuario);

}
