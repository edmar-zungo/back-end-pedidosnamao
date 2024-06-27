package com.edmarzungo.pedidosnamao.services.dtos;

import com.edmarzungo.pedidosnamao.domain.CardapioModel;
import com.edmarzungo.pedidosnamao.enumerations.EstadoItem;
import com.edmarzungo.pedidosnamao.enumerations.TipoBebida;
import com.edmarzungo.pedidosnamao.enumerations.TipoItemConsumo;

import java.time.ZonedDateTime;
import java.util.UUID;

public record ItemConsumoDTO(UUID id, String descricao, Double preco, EstadoItem estadoItemPedido, ZonedDateTime dataCriacao, String cozinha, String origem, ZonedDateTime dataActualizaca, TipoItemConsumo tipoItemConsumo, TipoBebida tipoBebida, CardapioModel cardapio) {
}
