package com.edmarzungo.pedidosnamao.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

@Entity
public class ItemPedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private ItemConsumoModel itemConsumoModel;
    @NotNull
    private long quantidadeItemConsumo;
    @NotNull
    private Double precoItemPedido;
    private Double desconto;
    private String descricao;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PedidoModel pedidoModel;

    public ItemPedidoModel() {
    }

    public ItemPedidoModel(UUID id, ItemConsumoModel itemConsumoModel, long quantidadeItemConsumo, Double precoItemPedido, Double desconto, String descricao, PedidoModel pedidoModel) {
        this.id = id;
        this.itemConsumoModel = itemConsumoModel;
        this.quantidadeItemConsumo = quantidadeItemConsumo;
        this.precoItemPedido = precoItemPedido;
        this.desconto = desconto;
        this.descricao = descricao;
        this.pedidoModel = pedidoModel;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ItemConsumoModel getItemConsumo() {
        return itemConsumoModel;
    }

    public void setItemConsumo(ItemConsumoModel itemConsumoModel) {
        this.itemConsumoModel = itemConsumoModel;
    }

    public long getQuantidadeItemConsumo() {
        return quantidadeItemConsumo;
    }

    public void setQuantidadeItemConsumo(long quantidadeItemConsumo) {
        this.quantidadeItemConsumo = quantidadeItemConsumo;
    }

    public Double getPrecoItemPedido() {
        return precoItemPedido;
    }

    public void setPrecoItemPedido(Double precoItemPedido) {
        this.precoItemPedido = precoItemPedido;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public PedidoModel getPedido() {
        return pedidoModel;
    }

    public void setPedido(PedidoModel pedidoModel) {
        this.pedidoModel = pedidoModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoModel that = (ItemPedidoModel) o;
        return quantidadeItemConsumo == that.quantidadeItemConsumo && Objects.equals(id, that.id) && Objects.equals(itemConsumoModel, that.itemConsumoModel) && Objects.equals(precoItemPedido, that.precoItemPedido) && Objects.equals(desconto, that.desconto) && Objects.equals(descricao, that.descricao) && Objects.equals(pedidoModel, that.pedidoModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemConsumoModel, quantidadeItemConsumo, precoItemPedido, desconto, descricao, pedidoModel);
    }

    @Override
    public String toString() {
        return "ItemPedidoModel{" +
                "id=" + id +
                ", itemConsumoModel=" + itemConsumoModel +
                ", quantidadeItemConsumo=" + quantidadeItemConsumo +
                ", precoItemPedido=" + precoItemPedido +
                ", desconto=" + desconto +
                ", descricao='" + descricao + '\'' +
                ", pedidos=" + pedidoModel +
                '}';
    }
}
