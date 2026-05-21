package com.Auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Auth.models.Recorrido;
import com.Auth.models.Usuario;

public interface RecorridoRepository extends JpaRepository<Recorrido, Long> {

    Optional<Recorrido> findByUsuarioAndCerradoFalse(Usuario usuario);


}
