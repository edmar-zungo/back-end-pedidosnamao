package com.edmarzungo.pedidosnamao.services.mappers;

import com.edmarzungo.pedidosnamao.domain.PedidoModel;
import com.edmarzungo.pedidosnamao.services.dtos.PedidoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "dataCriacao", source = "dataCriacao")
    @Mapping(target = "sequencia", source = "sequencia")
    @Mapping(target = "numero", source = "numero")
    @Mapping(target = "dataActualizacao", source = "dataActualizacao")
    @Mapping(target = "mesa", source = "mesa")
    @Mapping(target = "estadoPedido", source = "estadoPedido")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "enderecoEntregaDetalhado", source = "enderecoEntregaDetalhado")
    @Mapping(target = "tempoEntrega", source = "tempoEntrega")
    @Mapping(target = "descricaoEntrega", source = "descricaoEntrega")
    @Mapping(target = "valorEntrega", source = "valorEntrega")
    @Mapping(target = "totalPagar", source = "totalPagar")
    @Mapping(target = "totalPago", source = "totalPago")
    @Mapping(target = "totalTroco", source = "totalTroco")
    PedidoDTO pedidoToPedidoDTO(PedidoModel pedidoModel);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "dataCriacao", source = "dataCriacao")
    @Mapping(target = "sequencia", source = "sequencia")
    @Mapping(target = "numero", source = "numero")
    @Mapping(target = "dataActualizacao", source = "dataActualizacao")
    @Mapping(target = "mesa", source = "mesa")
    @Mapping(target = "estadoPedido", source = "estadoPedido")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "enderecoEntregaDetalhado", source = "enderecoEntregaDetalhado")
    @Mapping(target = "tempoEntrega", source = "tempoEntrega")
    @Mapping(target = "descricaoEntrega", source = "descricaoEntrega")
    @Mapping(target = "valorEntrega", source = "valorEntrega")
    @Mapping(target = "totalPagar", source = "totalPagar")
    @Mapping(target = "totalPago", source = "totalPago")
    @Mapping(target = "totalTroco", source = "totalTroco")
    PedidoModel pedidoDTOToPedidoModel(PedidoDTO pedidoDTO);
}
