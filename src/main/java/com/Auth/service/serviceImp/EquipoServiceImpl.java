package com.Auth.service.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Auth.enums.RolEquipo;
import com.Auth.models.Equipo;
import com.Auth.models.Usuario;
import com.Auth.models.UsuarioEquipo;
import com.Auth.repository.EquipoRepository;
import com.Auth.repository.UsuarioEquipoRepository;
import com.Auth.service.EquipoService;

@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private UsuarioEquipoRepository usuarioEquipoRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    public void ativarPower(Long equipoId, Usuario usuario) {

        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        UsuarioEquipo ue = usuarioEquipoRepository.findByUsuarioAndEquipo(usuario, equipo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (ue.getRol() != RolEquipo.CAPITAN) {
            throw new RuntimeException("Usuario no es capitan");
        }
    }

}
