package com.edmarzungo.pedidosnamao.services.dtos;

import com.edmarzungo.pedidosnamao.enumerations.EstadoItem;

import java.util.UUID;

public record MesaDTO(UUID id, Long numero,Long sequencia, Long quantidadeLugares, EstadoItem estadoMesa, String descricao) {
}
