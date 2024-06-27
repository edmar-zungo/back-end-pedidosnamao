package com.edmarzungo.pedidosnamao.services.mappers;

import com.edmarzungo.pedidosnamao.domain.MesaModel;
import com.edmarzungo.pedidosnamao.services.dtos.MesaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MesaMapper{
    @Mapping(target = "id", source = "id")
    @Mapping(target = "numero", source = "numero")
    @Mapping(target = "sequencia", source = "sequencia")
    @Mapping(target = "quantidadeLugares", source = "quantidadeLugares")
    @Mapping(target = "estadoMesa", source = "estadoMesa")
    @Mapping(target = "descricao", source = "descricao")
    MesaDTO mesaToMesaDTO(MesaModel mesaModel);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "numero", source = "numero")
    @Mapping(target = "sequencia", source = "sequencia")
    @Mapping(target = "quantidadeLugares", source = "quantidadeLugares")
    @Mapping(target = "estadoMesa", source = "estadoMesa")
    @Mapping(target = "descricao", source = "descricao")
    MesaModel mesaDTOToMesaModel(MesaDTO mesaDTO);
}
