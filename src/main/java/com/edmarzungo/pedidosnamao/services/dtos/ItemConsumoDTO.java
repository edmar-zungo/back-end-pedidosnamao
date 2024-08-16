package com.edmarzungo.pedidosnamao.services.dtos;

import com.edmarzungo.pedidosnamao.domain.CardapioModel;
import com.edmarzungo.pedidosnamao.domain.ItemPedidoModel;
import com.edmarzungo.pedidosnamao.enumerations.EstadoItem;
import com.edmarzungo.pedidosnamao.enumerations.TipoBebida;
import com.edmarzungo.pedidosnamao.enumerations.TipoItemConsumo;
import com.edmarzungo.pedidosnamao.enumerations.TipoPrato;

import java.time.ZonedDateTime;
import java.util.UUID;

public record ItemConsumoDTO(UUID id, byte[] imagem, String imagemContentType,String descricao, Double preco, EstadoItem estadoItemPedido,
                             ZonedDateTime dataCriacao, String cozinha, String origem, ZonedDateTime dataActualizacao,
                             TipoItemConsumo tipoItemConsumo,TipoPrato tipoPrato, TipoBebida tipoBebida, CardapioModel cardapio,
                             ItemPedidoModel itemPedido) {}
