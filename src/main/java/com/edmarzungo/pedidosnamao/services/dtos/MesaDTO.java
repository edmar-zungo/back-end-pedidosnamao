package com.edmarzungo.pedidosnamao.services.dtos;

import com.edmarzungo.pedidosnamao.enumerations.EstadoItem;

import java.util.UUID;

public record MesaDTO(UUID id, long numero, long quantidadeLugares, EstadoItem estadoMesa, String descricao) {
}
