package com.edmarzungo.pedidosnamao.services.dtos;

import com.edmarzungo.pedidosnamao.domain.ItemConsumoModel;
import com.edmarzungo.pedidosnamao.domain.PedidoModel;

import java.util.UUID;

public record ItemPedidoDTO(UUID id, ItemConsumoModel itemConsumo, Long quantidadeItemConsumo, Double precoItemPedido, Double desconto, String descricao, PedidoModel pedido) {
}
