package com.edmarzungo.pedidosnamao.services.mappers;

import com.edmarzungo.pedidosnamao.domain.MesaModel;
import com.edmarzungo.pedidosnamao.services.dtos.MesaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MesaMapper {

    MesaMapper INSTANCE = Mappers.getMapper(MesaMapper.class);

    MesaDTO mesaToMesaDTO(MesaModel mesaModel);
    MesaModel mesaDTOToMesaModel(MesaDTO mesaDTO);
}
