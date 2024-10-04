package com.edmarzungo.pedidosnamao.services;

import com.edmarzungo.pedidosnamao.enumerations.EstadoPedido;
import com.edmarzungo.pedidosnamao.services.dtos.CardapioDTO;
import com.edmarzungo.pedidosnamao.services.dtos.PedidoDTO;
import com.edmarzungo.pedidosnamao.services.dtos.PedidoPageDTO;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

public interface PedidoService {

    PedidoDTO save(PedidoDTO pedidoDTO);
    PedidoDTO update(PedidoDTO pedidoDTO, UUID pedidoId);

    List<PedidoDTO> getAll();
    PedidoPageDTO getAllPageble(int pageNumber, int pageItens);
    PedidoDTO getOne(UUID pedidoId);
    void delete(UUID pedidoId);

    PedidoDTO init();

    PedidoDTO mudarEstadoPedido(PedidoDTO pedidoDTO);
    PedidoDTO calculaTotalPagar(UUID pedidoId);
}
