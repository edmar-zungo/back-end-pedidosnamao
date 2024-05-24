package com.edmarzungo.pedidosnamao.domain;

import com.edmarzungo.pedidosnamao.enumerations.EstadoItem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private long numero;
    private long quantidadeLugares;
    private EstadoItem estadoItem;
    private String descricao;

    public Mesa() {
    }

    public Mesa(UUID id, long numero, long quantidadeLugares, EstadoItem estadoItem, String descricao) {
        this.id = id;
        this.numero = numero;
        this.quantidadeLugares = quantidadeLugares;
        this.estadoItem = estadoItem;
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

    public EstadoItem getEstadoItem() {
        return estadoItem;
    }

    public void setEstadoItem(EstadoItem estadoItem) {
        this.estadoItem = estadoItem;
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
        Mesa mesa = (Mesa) o;
        return numero == mesa.numero && quantidadeLugares == mesa.quantidadeLugares && Objects.equals(id, mesa.id) && estadoItem == mesa.estadoItem && Objects.equals(descricao, mesa.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero, quantidadeLugares, estadoItem, descricao);
    }

    @Override
    public String toString() {
        return "Mesa{" +
                "id=" + id +
                ", numero=" + numero +
                ", quantidadeLugares=" + quantidadeLugares +
                ", estadoItem=" + estadoItem +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
