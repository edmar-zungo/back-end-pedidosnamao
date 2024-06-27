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
    private long numero;
    @NotNull
    private long quantidadeLugares;
    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoItem estadoMesa;
    @NotNull
    private String descricao;

    public MesaModel() {
    }

    public MesaModel(UUID id, long numero, long quantidadeLugares, EstadoItem estadoMesa, String descricao) {
        this.id = id;
        this.numero = numero;
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

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public long getQuantidadeLugares() {
        return quantidadeLugares;
    }

    public void setQuantidadeLugares(long quantidadeLugares) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MesaModel mesaModel = (MesaModel) o;
        return numero == mesaModel.numero && quantidadeLugares == mesaModel.quantidadeLugares && Objects.equals(id, mesaModel.id) && estadoMesa == mesaModel.estadoMesa && Objects.equals(descricao, mesaModel.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero, quantidadeLugares, estadoMesa, descricao);
    }

    @Override
    public String toString() {
        return "MesaModel{" +
                "id=" + id +
                ", numero=" + numero +
                ", quantidadeLugares=" + quantidadeLugares +
                ", estadoItem=" + estadoMesa +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
