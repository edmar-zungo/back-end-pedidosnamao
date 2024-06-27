package com.edmarzungo.pedidosnamao.services.dtos;

import com.edmarzungo.pedidosnamao.enumerations.TipoItemConsumo;

import java.util.UUID;

public record CardapioDTO(UUID id, String descricao, TipoItemConsumo tipoItemConsumo) {
}
