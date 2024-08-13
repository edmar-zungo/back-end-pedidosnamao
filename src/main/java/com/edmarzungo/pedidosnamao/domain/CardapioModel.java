package com.edmarzungo.pedidosnamao.domain;

import com.edmarzungo.pedidosnamao.enumerations.TipoCardapio;
import com.edmarzungo.pedidosnamao.enumerations.TipoItemConsumo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

@Entity
public class CardapioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String descricao;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoCardapio tipoCardapio;


    public CardapioModel() {
    }

    public CardapioModel(UUID id, String descricao, TipoCardapio tipoCardapio) {
        this.id = id;
        this.descricao = descricao;
        this.tipoCardapio = tipoCardapio;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoCardapio getTipoCardapio() {
        return tipoCardapio;
    }

    public void setTipoCardapio(TipoCardapio tipoCardapio) {
        this.tipoCardapio = tipoCardapio;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardapioModel cardapioModel = (CardapioModel) o;
        return Objects.equals(id, cardapioModel.id) && Objects.equals(descricao, cardapioModel.descricao) && tipoCardapio == cardapioModel.tipoCardapio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, tipoCardapio);
    }

    @Override
    public String toString() {
        return "CardapioModel{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", tipoCardapio=" + tipoCardapio +
                '}';
    }


}
