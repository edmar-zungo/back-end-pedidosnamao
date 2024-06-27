package com.edmarzungo.pedidosnamao.services.dtos;

import com.edmarzungo.pedidosnamao.domain.MesaModel;
import com.edmarzungo.pedidosnamao.enumerations.EstadoPedido;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public record PedidoDTO(UUID id, LocalDateTime dataCriacao, Long sequencia ,LocalDateTime dataActualizacao, MesaModel mesa, EstadoPedido estadoPedido, String descricao, boolean isDeliver, String enderecoDetalhado, LocalTime tempoEntrega, String descricaoEntrega, Double valorEntrega, Double totalPagar, Double totalPago, Double totalTroco) {
}