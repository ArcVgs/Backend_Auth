package com.Auth.enums;

import java.util.Arrays;

public enum EstadosUsuario {
    ACTIVO(1),
    INACTIVO(2);

    private final int valor;

    EstadosUsuario(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static boolean esActivo(int valor) {
        return Arrays.stream(values()).anyMatch(e -> e.getValor() == valor);
    }
}
