package com.edmarzungo.pedidosnamao.services.mappers;

import com.edmarzungo.pedidosnamao.domain.PedidoModel;
import com.edmarzungo.pedidosnamao.services.dtos.PedidoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    PedidoMapper INSTANDE = Mappers.getMapper(PedidoMapper.class);

    PedidoDTO pedidoToPedidoDTO(PedidoModel pedidoModel);
    PedidoModel pedidoDTOToPedidoModel(PedidoDTO pedidoDTO);
}
