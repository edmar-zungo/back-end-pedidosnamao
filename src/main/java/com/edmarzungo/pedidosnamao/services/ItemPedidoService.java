package com.edmarzungo.pedidosnamao.services;

import com.edmarzungo.pedidosnamao.services.dtos.ItemConsumoDTO;
import com.edmarzungo.pedidosnamao.services.dtos.ItemPedidoDTO;
import com.edmarzungo.pedidosnamao.services.dtos.PedidoDTO;

import java.util.List;
import java.util.UUID;

public interface ItemPedidoService {


    ItemPedidoDTO save(ItemPedidoDTO itemPedidoDTO);
    ItemPedidoDTO update(ItemPedidoDTO itemPedidoDTO, UUID itemPedidoId);

    List<ItemPedidoDTO> getAll();
    ItemPedidoDTO getOne(UUID itemPedidoId);
    void delete(UUID itemPedidoId);

    ItemPedidoDTO init(ItemConsumoDTO itemConsumoDTO);
    List<ItemPedidoDTO> itensPedidoByPedido(UUID pedidoId);
}
