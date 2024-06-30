package com.edmarzungo.pedidosnamao.services;

import com.edmarzungo.pedidosnamao.services.dtos.CardapioDTO;
import com.edmarzungo.pedidosnamao.services.dtos.MesaDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardapioService {

    CardapioDTO save(CardapioDTO cardapioDTO);
    CardapioDTO update(CardapioDTO cardapioDTO, UUID cardapioId);

    List<CardapioDTO> getAll();
    CardapioDTO getOne(UUID cardapioId);
    void delete(UUID cardapioId);

}
