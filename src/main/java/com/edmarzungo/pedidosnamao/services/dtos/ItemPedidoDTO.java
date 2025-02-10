package com.edmarzungo.pedidosnamao.services.dtos;

import com.edmarzungo.pedidosnamao.domain.ItemConsumoModel;
import com.edmarzungo.pedidosnamao.domain.PedidoModel;

import java.util.UUID;

public class ItemPedidoDTO {
    private UUID id;
    private ItemConsumoModel itemConsumo;
    private Long quantidadeItemConsumo;
    private Double precoItemPedido;
    private Double desconto;
    private String descricao;
    private PedidoModel pedido;

    public ItemPedidoDTO() {
    }

    public ItemPedidoDTO(UUID id, ItemConsumoModel itemConsumo, Long quantidadeItemConsumo, Double precoItemPedido,
                         Double desconto, String descricao, PedidoModel pedido) {
        this.id = id;
        this.itemConsumo = itemConsumo;
        this.quantidadeItemConsumo = quantidadeItemConsumo;
        this.precoItemPedido = precoItemPedido;
        this.desconto = desconto;
        this.descricao = descricao;
        this.pedido = pedido;
    }



    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public ItemConsumoModel getItemConsumo() { return itemConsumo; }
    public void setItemConsumo(ItemConsumoModel itemConsumo) { this.itemConsumo = itemConsumo; }

    public Long getQuantidadeItemConsumo() { return quantidadeItemConsumo; }
    public void setQuantidadeItemConsumo(Long quantidadeItemConsumo) { this.quantidadeItemConsumo = quantidadeItemConsumo; }

    public Double getPrecoItemPedido() { return precoItemPedido; }
    public void setPrecoItemPedido(Double precoItemPedido) { this.precoItemPedido = precoItemPedido; }

    public Double getDesconto() { return desconto; }
    public void setDesconto(Double desconto) { this.desconto = desconto; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public PedidoModel getPedido() { return pedido; }
    public void setPedido(PedidoModel pedido) { this.pedido = pedido; }
}
