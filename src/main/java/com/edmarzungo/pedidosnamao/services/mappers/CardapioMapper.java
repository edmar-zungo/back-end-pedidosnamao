package com.edmarzungo.pedidosnamao.services.mappers;

import com.edmarzungo.pedidosnamao.domain.CardapioModel;
import com.edmarzungo.pedidosnamao.services.dtos.CardapioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CardapioMapper {

    CardapioMapper INSTANCE = Mappers.getMapper(CardapioMapper.class);

    CardapioDTO cardapioTocardapioDTO(CardapioModel cardapioModel);
    CardapioModel cardapioDTOToCardapioModel(CardapioDTO cardapioDTO);
}
