package com.edmarzungo.pedidosnamao.domain;

import com.edmarzungo.pedidosnamao.enumerations.EstadoItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

@Entity
public class MesaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Long numero;
    @NotNull
    private Long sequencia;
    @NotNull
    private Long quantidadeLugares;
    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoItem estadoMesa;
    @NotNull
    private String descricao;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private PedidoModel pedido;

    public MesaModel() {
    }

    public MesaModel(UUID id, long numero, long sequencia, long quantidadeLugares, EstadoItem estadoMesa, String descricao) {
        this.id = id;
        this.numero = numero;
        this.sequencia = sequencia;
        this.quantidadeLugares = quantidadeLugares;
        this.estadoMesa = estadoMesa;
        this.descricao = descricao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }
    public Long getSequencia() {
        return sequencia;
    }

    public void setSequencia(Long sequencia) {
        this.sequencia = sequencia;
    }

    public Long getQuantidadeLugares() {
        return quantidadeLugares;
    }

    public void setQuantidadeLugares(Long quantidadeLugares) {
        this.quantidadeLugares = quantidadeLugares;
    }

    public EstadoItem getEstadoMesa() {
        return estadoMesa;
    }

    public void setEstadoMesa(EstadoItem estadoMesa) {
        this.estadoMesa = estadoMesa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public PedidoModel getPedido() {
        return pedido;
    }

    public void setPedido(PedidoModel pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MesaModel mesaModel = (MesaModel) o;
        return numero.equals(mesaModel.numero) && sequencia.equals(mesaModel.sequencia) && quantidadeLugares.equals(mesaModel.quantidadeLugares) && Objects.equals(id, mesaModel.id) && estadoMesa == mesaModel.estadoMesa && Objects.equals(descricao, mesaModel.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero, sequencia,quantidadeLugares, estadoMesa, descricao);
    }

    @Override
    public String toString() {
        return "MesaModel{" +
                "id=" + id +
                ", numero=" + numero +
                "sequencia="+ sequencia +
                ", quantidadeLugares=" + quantidadeLugares +
                ", estadoItem=" + estadoMesa +
                ", descricao='" + descricao + '\'' +
                '}';
    }



}
