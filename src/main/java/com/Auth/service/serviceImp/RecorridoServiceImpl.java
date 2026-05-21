package com.Auth.service.serviceImp;

import org.springframework.stereotype.Service;

import com.Auth.models.Recorrido;
import com.Auth.models.Usuario;
import com.Auth.repository.PuntoGPSRepository;
import com.Auth.repository.RecorridoRepository;
import com.Auth.service.RecorridoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecorridoServiceImpl implements RecorridoService {

    private final RecorridoRepository recorridoRepository;

    private final PuntoGPSRepository puntoGPSRepository;

    @Override
    public Recorrido iniciarRecorrido(Usuario usuario) {

        recorridoRepository.findByUsuarioAndCerradoFalse(usuario).ifPresent(r -> {
            throw new RuntimeException("Ya tienes un recorrido abierto");
        });

        Recorrido recorrido = new Recorrido();
        recorrido.setUsuario(usuario);
        recorrido.setCerrado(false);
        recorrido.setArea(0.0);

        return recorridoRepository.save(recorrido);

    }

    @Override
    public Recorrido obtenerRecorridoActivo(Usuario usuario) {
        return recorridoRepository.findByUsuarioAndCerradoFalse(usuario)
                .orElseThrow(() -> new RuntimeException("No tienes un recorrido abierto"));
    }

    @Override
    public Recorrido cerrarRecorrido(Long recorridoId, Usuario usuario) {

        Recorrido recorrido = recorridoRepository.findById(recorridoId)
                .orElseThrow(() -> new RuntimeException("No se encontro el recorrido"));

        if (!recorrido.getUsuario().getId().equals(usuario.getId())) {
            throw new RuntimeException("No puedes cerrar el recorrido de otro usuario");
        }

        if (recorrido.isCerrado()) {
            throw new RuntimeException("El recorrido ya esta cerrado");
        }

        Double area = calcularArea(recorrido);

        recorrido.setArea(area);
        recorrido.setCerrado(true);
        return recorridoRepository.save(recorrido);

    }

    private Double calcularArea(Recorrido recorrido) {
        return 0.0;
    }

}
