package com.edmarzungo.pedidosnamao.domain;

import com.edmarzungo.pedidosnamao.enumerations.TipoItemConsumo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Entity
public class CardapioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String descricao;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoItemConsumo tipoItemConsumo;

    public CardapioModel() {
    }

    public CardapioModel(UUID id, String descricao, TipoItemConsumo tipoItemConsumo) {
        this.id = id;
        this.descricao = descricao;
        this.tipoItemConsumo = tipoItemConsumo;
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

    public TipoItemConsumo getTipoItemConsumo() {
        return tipoItemConsumo;
    }

    public void setTipoItemConsumo(TipoItemConsumo tipoItemConsumo) {
        this.tipoItemConsumo = tipoItemConsumo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardapioModel cardapioModel = (CardapioModel) o;
        return Objects.equals(id, cardapioModel.id) && Objects.equals(descricao, cardapioModel.descricao) && tipoItemConsumo == cardapioModel.tipoItemConsumo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, tipoItemConsumo);
    }

    @Override
    public String toString() {
        return "CardapioModel{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", tipoItemConsumo=" + tipoItemConsumo +
                '}';
    }
}
