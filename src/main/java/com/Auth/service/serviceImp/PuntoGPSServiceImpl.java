package com.Auth.service.serviceImp;

import org.springframework.stereotype.Service;

import com.Auth.models.PuntosGPS;
import com.Auth.models.Recorrido;
import com.Auth.models.Usuario;
import com.Auth.repository.PuntoGPSRepository;
import com.Auth.repository.RecorridoRepository;
import com.Auth.service.PuntoGPSService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PuntoGPSServiceImpl implements PuntoGPSService {

    private final RecorridoRepository recorridoRepository;
    private final PuntoGPSRepository puntoGPSRepository;

    @Override
    public void agregarPunto(Usuario usuario, Double lat, Double lng) {

        Recorrido recorrido = recorridoRepository
                .findByUsuarioAndCerradoFalse(usuario)
                .orElseThrow(() -> new RuntimeException("No tienes un recorrido activo"));

        int orden = puntoGPSRepository
                .findByRecorridoOrderByOrdenAsc(recorrido)
                .size() + 1;

        PuntosGPS punto = new PuntosGPS();
        punto.setRecorrido(recorrido);
        punto.setLat(lat);
        punto.setLng(lng);
        punto.setOrden(orden);

        puntoGPSRepository.save(punto);
    }
}