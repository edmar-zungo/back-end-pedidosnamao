package com.edmarzungo.pedidosnamao.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

@Entity
public class ItemPedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_consumo_id")
    private ItemConsumoModel itemConsumo;
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

    public ItemPedidoModel(UUID id, ItemConsumoModel itemConsumo, long quantidadeItemConsumo, Double precoItemPedido, Double desconto, String descricao, PedidoModel pedidoModel) {
        this.id = id;
        this.itemConsumo = itemConsumo;
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
        return itemConsumo;
    }

    public void setItemConsumo(ItemConsumoModel itemConsumo) {
        this.itemConsumo = itemConsumo;
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
        return quantidadeItemConsumo == that.quantidadeItemConsumo && Objects.equals(id, that.id) && Objects.equals(itemConsumo, that.itemConsumo) && Objects.equals(precoItemPedido, that.precoItemPedido) && Objects.equals(desconto, that.desconto) && Objects.equals(descricao, that.descricao) && Objects.equals(pedidoModel, that.pedidoModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemConsumo, quantidadeItemConsumo, precoItemPedido, desconto, descricao, pedidoModel);
    }

    @Override
    public String toString() {
        return "ItemPedidoModel{" +
                "id=" + id +
                ", itemConsumoModel=" + itemConsumo +
                ", quantidadeItemConsumo=" + quantidadeItemConsumo +
                ", precoItemPedido=" + precoItemPedido +
                ", desconto=" + desconto +
                ", descricao='" + descricao + '\'' +
                ", pedidos=" + pedidoModel +
                '}';
    }
}
