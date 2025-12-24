package com.Auth.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Auth.enums.TipoPower;
import com.Auth.models.EquipoPower;

public interface EquipoPowerRepository extends JpaRepository<EquipoPower, Long> {

    boolean existsByEquipoIdAndPowerAndActivoHastaAfter(
            Long equipoId,
            TipoPower power,
            LocalDateTime now);

}
