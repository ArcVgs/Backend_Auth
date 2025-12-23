package com.Auth.models;

import java.time.LocalDateTime;

import com.Auth.enums.TipoPower;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class EquipoPower extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Equipo equipo;

    @Enumerated(EnumType.STRING)
    private TipoPower power;

    private LocalDateTime activoDesde;

    private LocalDateTime activoHasta;

}
