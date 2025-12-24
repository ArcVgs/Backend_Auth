package com.Auth.service.serviceImp;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Auth.dto.ActivarPowerRequest;
import com.Auth.dto.PowerResponse;
import com.Auth.enums.RolEquipo;
import com.Auth.models.Equipo;
import com.Auth.models.EquipoPower;
import com.Auth.models.UsuarioEquipo;
import com.Auth.repository.EquipoPowerRepository;
import com.Auth.repository.EquipoRepository;
import com.Auth.repository.UsuarioEquipoRepository;
import com.Auth.service.EquipoPowerService;

@Service
public class EquipoPowerServiceImpl implements EquipoPowerService {

    @Autowired
    private UsuarioEquipoRepository usuarioEquipoRepository;

    @Autowired
    private EquipoPowerRepository equipoPowerRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    public PowerResponse activarPower(Long usuarioId, ActivarPowerRequest request) {

        UsuarioEquipo ue = usuarioEquipoRepository.findByUsuarioIdAndEquipoId(usuarioId, request.getEquipoId())
                .orElseThrow(() -> new RuntimeException("No pertenece al equipo"));

        if (ue.getRol() != RolEquipo.CAPITAN) {
            throw new RuntimeException("Solo el capitan puede activar el power");
        }

        boolean activo = equipoPowerRepository.existsByEquipoIdAndPowerAndActivoHastaAfter(request.getEquipoId(),
                request.getTipoPower(), LocalDateTime.now());

        if (activo) {
            throw new RuntimeException("El power ya esta activo");
        }

        Equipo equipo = equipoRepository.findById(request.getEquipoId())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        EquipoPower equipoPower = new EquipoPower();
        equipoPower.setEquipo(equipo);
        equipoPower.setPower(request.getTipoPower());
        equipoPower.setActivoDesde(LocalDateTime.now());
        equipoPower.setActivoHasta(LocalDateTime.now().plusMinutes(request.getDuracionMinutos()));
        equipoPower.setEstado(1);
        equipoPowerRepository.save(equipoPower);

        return PowerResponse.builder()
                .equipo(equipo.getNombre())
                .power(request.getTipoPower().name())
                .activoDesde(equipoPower.getActivoDesde())
                .activoHasta(equipoPower.getActivoHasta())
                .build();

    }

}
