package com.edmarzungo.pedidosnamao.services.mappers;

import com.edmarzungo.pedidosnamao.domain.ItemConsumoModel;
import com.edmarzungo.pedidosnamao.services.dtos.ItemConsumoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemConsumoMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "imagem", source = "imagem")
    @Mapping(target = "imagemContentType", source = "imagemContentType")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "preco", source = "preco")
    @Mapping(target = "estadoItemPedido", source = "estadoItemPedido")
    @Mapping(target = "dataCriacao", source = "dataCriacao")
    @Mapping(target = "cozinha", source = "cozinha")
    @Mapping(target = "origem", source = "origem")
    @Mapping(target = "dataActualizacao", source = "dataActualizacao")
    @Mapping(target = "tipoItemConsumo", source = "tipoItemConsumo")
    @Mapping(target = "tipoBebida", source = "tipoBebida")
    @Mapping(target = "cardapio", source = "cardapio")
    ItemConsumoDTO itemConsumoModelToItemConsumoDTO(ItemConsumoModel itemConsumoModel);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "imagem", source = "imagem")
    @Mapping(target = "imagemContentType", source = "imagemContentType")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "preco", source = "preco")
    @Mapping(target = "estadoItemPedido", source = "estadoItemPedido")
    @Mapping(target = "dataCriacao", source = "dataCriacao")
    @Mapping(target = "cozinha", source = "cozinha")
    @Mapping(target = "origem", source = "origem")
    @Mapping(target = "dataActualizacao", source = "dataActualizacao")
    @Mapping(target = "tipoItemConsumo", source = "tipoItemConsumo")
    @Mapping(target = "tipoBebida", source = "tipoBebida")
    ItemConsumoModel itemConsumoDTOToItemConsumoModel(ItemConsumoDTO itemConsumoDTO);
}
