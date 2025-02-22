package com.edmarzungo.pedidosnamao.services.mappers;

import com.edmarzungo.pedidosnamao.domain.CardapioModel;
import com.edmarzungo.pedidosnamao.services.dtos.CardapioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CardapioMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "tipoCardapio", source = "tipoCardapio")
    CardapioDTO cardapioTocardapioDTO(CardapioModel cardapioModel);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "tipoCardapio", source = "tipoCardapio")
    CardapioModel cardapioDTOToCardapioModel(CardapioDTO cardapioDTO);
}
