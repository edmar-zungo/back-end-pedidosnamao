package com.edmarzungo.pedidosnamao.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private ItemConsumo itemConsumo;
    @NotNull
    private long quantidadeItemConsumo;
    @NotNull
    private Double precoItemPedido;
    private Double desconto;
    private String descricao;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

    public ItemPedido() {
    }

    public ItemPedido(UUID id, ItemConsumo itemConsumo, long quantidadeItemConsumo, Double precoItemPedido, Double desconto, String descricao, Set<Pedido> pedidos) {
        this.id = id;
        this.itemConsumo = itemConsumo;
        this.quantidadeItemConsumo = quantidadeItemConsumo;
        this.precoItemPedido = precoItemPedido;
        this.desconto = desconto;
        this.descricao = descricao;
        this.pedidos = pedidos;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ItemConsumo getItemConsumo() {
        return itemConsumo;
    }

    public void setItemConsumo(ItemConsumo itemConsumo) {
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

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return quantidadeItemConsumo == that.quantidadeItemConsumo && Objects.equals(id, that.id) && Objects.equals(itemConsumo, that.itemConsumo) && Objects.equals(precoItemPedido, that.precoItemPedido) && Objects.equals(desconto, that.desconto) && Objects.equals(descricao, that.descricao) && Objects.equals(pedidos, that.pedidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemConsumo, quantidadeItemConsumo, precoItemPedido, desconto, descricao, pedidos);
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "id=" + id +
                ", itemConsumo=" + itemConsumo +
                ", quantidadeItemConsumo=" + quantidadeItemConsumo +
                ", precoItemPedido=" + precoItemPedido +
                ", desconto=" + desconto +
                ", descricao='" + descricao + '\'' +
                ", pedidos=" + pedidos +
                '}';
    }
}
