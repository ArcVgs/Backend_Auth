package com.Auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Auth.models.PuntosGPS;
import com.Auth.models.Recorrido;

public interface PuntoGPSRepository extends JpaRepository<PuntosGPS, Long> {

    List<PuntosGPS> findByRecorridoOrderByOrdenAsc(Recorrido recorrido);

}
