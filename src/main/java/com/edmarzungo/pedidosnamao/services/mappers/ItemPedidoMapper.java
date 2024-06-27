package com.edmarzungo.pedidosnamao.services.mappers;

import com.edmarzungo.pedidosnamao.domain.ItemPedidoModel;
import com.edmarzungo.pedidosnamao.services.dtos.ItemPedidoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "itemConsumo", source = "itemConsumo")
    @Mapping(target = "quantidadeItemConsumo", source = "quantidadeItemConsumo")
    @Mapping(target = "precoItemPedido", source = "precoItemPedido")
    @Mapping(target = "desconto", source = "desconto")
    @Mapping(target = "descricao", source = "descricao")
    ItemPedidoModel itemPedidoDTOToItemPedidoModel(ItemPedidoDTO pedidoDTO);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "itemConsumo", source = "itemConsumo")
    @Mapping(target = "quantidadeItemConsumo", source = "quantidadeItemConsumo")
    @Mapping(target = "precoItemPedido", source = "precoItemPedido")
    @Mapping(target = "desconto", source = "desconto")
    @Mapping(target = "descricao", source = "descricao")
    ItemPedidoDTO itemPedidoModelToItemPedidoDTO(ItemPedidoModel pedidoModel);
}
