package com.models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private Timestamp fechaRegistro;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 1")
    private Integer estado;

    public BaseEntity() {
        this.estado = 1;
    }

}
