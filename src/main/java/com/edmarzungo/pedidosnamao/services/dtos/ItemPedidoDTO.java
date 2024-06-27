package com.edmarzungo.pedidosnamao.services.dtos;

import com.edmarzungo.pedidosnamao.domain.ItemConsumoModel;

import java.util.UUID;

public record ItemPedidoDTO(UUID id, ItemConsumoModel itemConsumo, long quantidadeItemConsumo, Double precoItemPedido, Double desconto, String descricao) {
}
