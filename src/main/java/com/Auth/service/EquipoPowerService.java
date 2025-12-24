package com.Auth.service;

import com.Auth.dto.ActivarPowerRequest;
import com.Auth.dto.PowerResponse;

public interface EquipoPowerService {

    PowerResponse activarPower(Long usuarioId, ActivarPowerRequest request);

}
