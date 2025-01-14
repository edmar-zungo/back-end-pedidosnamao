package com.edmarzungo.pedidosnamao.services;

import com.edmarzungo.pedidosnamao.services.dtos.MesaDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MesaService {
    MesaDTO save(MesaDTO mesa);
    MesaDTO update(MesaDTO mesa, UUID id);

    List<MesaDTO> getAll();
    MesaDTO getOne(UUID mesaId);
    void delete(UUID mesaId);

    long gerarSequencia();

    MesaDTO init(MesaDTO mesaDTO);

}
