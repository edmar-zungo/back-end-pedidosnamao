package com.edmarzungo.pedidosnamao.services.mappers;

import com.edmarzungo.pedidosnamao.domain.ItemConsumoModel;
import com.edmarzungo.pedidosnamao.services.dtos.ItemConsumoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemConsumoMapper {
    ItemConsumoMapper INSTANCE = Mappers.getMapper(ItemConsumoMapper.class);
    ItemConsumoDTO itemConsumoModelToItemConsumoDTO(ItemConsumoModel itemConsumoModel);
    ItemConsumoModel itemConsumoDTOToItemConsumoModel(ItemConsumoDTO itemConsumoDTO);
}
