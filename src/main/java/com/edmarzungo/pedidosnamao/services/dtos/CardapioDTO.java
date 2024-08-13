package com.edmarzungo.pedidosnamao.services.dtos;

import com.edmarzungo.pedidosnamao.domain.ItemConsumoModel;
import com.edmarzungo.pedidosnamao.enumerations.TipoCardapio;
import com.edmarzungo.pedidosnamao.enumerations.TipoItemConsumo;

import java.util.Set;
import java.util.UUID;

public record CardapioDTO(UUID id, String descricao, TipoCardapio tipoCardapio) {
}
