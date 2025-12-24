package com.Auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Auth.models.Equipo;
import com.Auth.models.Usuario;
import com.Auth.models.UsuarioEquipo;

public interface UsuarioEquipoRepository extends JpaRepository<UsuarioEquipo, Long> {

    Optional<UsuarioEquipo> findByUsuarioAndEquipo(Usuario usuario, Equipo equipo);

    Optional<UsuarioEquipo> findByUsuarioIdAndEquipoId(Long usuarioId, Long equipoId);

}
