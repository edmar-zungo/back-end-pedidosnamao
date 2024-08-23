package com.edmarzungo.pedidosnamao.services;

import com.edmarzungo.pedidosnamao.services.dtos.CardapioDTO;
import com.edmarzungo.pedidosnamao.services.dtos.ItemConsumoDTO;

import java.util.List;
import java.util.UUID;

public interface ItemConsumoService {
    ItemConsumoDTO save(ItemConsumoDTO itemConsumoDTO);
    ItemConsumoDTO update(ItemConsumoDTO itemConsumoDTO, UUID itemConsumoId);

    List<ItemConsumoDTO> getAll();
    List<ItemConsumoDTO> getItensConsumoByCardapio(UUID cardapioId);
    ItemConsumoDTO getOne(UUID itemConsumoId);
    void delete(UUID itemConsumoId);
}
