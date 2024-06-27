package com.edmarzungo.pedidosnamao.services.mappers;

import com.edmarzungo.pedidosnamao.domain.ItemPedidoModel;
import com.edmarzungo.pedidosnamao.services.dtos.ItemPedidoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {
    ItemPedidoMapper INSTANCE = Mappers.getMapper(ItemPedidoMapper.class);
    ItemPedidoModel itemPedidoDTOToItemPedidoModel(ItemPedidoDTO pedidoDTO);
    ItemPedidoDTO itemPedidoModelToItemPedidoDTO(ItemPedidoModel pedidoModel);
}
