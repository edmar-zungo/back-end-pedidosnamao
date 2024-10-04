package com.edmarzungo.pedidosnamao.services.dtos;

import java.util.List;

public record PedidoPageDTO(List<PedidoDTO> pedidoDTOList, long totalItens, int totalPaginas) {}
