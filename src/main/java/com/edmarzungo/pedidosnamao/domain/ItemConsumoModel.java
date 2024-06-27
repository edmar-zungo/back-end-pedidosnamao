package com.edmarzungo.pedidosnamao.domain;

import com.edmarzungo.pedidosnamao.enumerations.EstadoItem;
import com.edmarzungo.pedidosnamao.enumerations.TipoBebida;
import com.edmarzungo.pedidosnamao.enumerations.TipoItemConsumo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Entity
public class ItemConsumoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String descricao;
    @NotNull
    private Double preco;
    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoItem estadoItemPedido;
    @NotNull
    private ZonedDateTime dataCriacao;

    private String cozinha;

    private String origem;
    @NotNull
    private ZonedDateTime dataActualizaca;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoItemConsumo tipoItemConsumo;

    @Enumerated(EnumType.STRING)
    private TipoBebida tipoBebida;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardapio_id")
    private CardapioModel cardapio;

    public ItemConsumoModel() {
    }

    public ItemConsumoModel(UUID id, String descricao, Double preco, EstadoItem estadoItemPedido, ZonedDateTime dataCriacao, String cozinha, String origem, ZonedDateTime dataActualizaca, TipoItemConsumo tipoItemConsumo, TipoBebida tipoBebida, CardapioModel cardapio) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.estadoItemPedido = estadoItemPedido;
        this.dataCriacao = dataCriacao;
        this.cozinha = cozinha;
        this.origem = origem;
        this.dataActualizaca = dataActualizaca;
        this.tipoItemConsumo = tipoItemConsumo;
        this.tipoBebida = tipoBebida;
        this.cardapio = cardapio;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public EstadoItem getEstadoItemPedido() {
        return estadoItemPedido;
    }

    public void setEstadoItemPedido(EstadoItem estadoItemPedido) {
        this.estadoItemPedido = estadoItemPedido;
    }

    public ZonedDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(ZonedDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCozinha() {
        return cozinha;
    }

    public void setCozinha(String cozinha) {
        this.cozinha = cozinha;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public ZonedDateTime getDataActualizaca() {
        return dataActualizaca;
    }

    public void setDataActualizaca(ZonedDateTime dataActualizaca) {
        this.dataActualizaca = dataActualizaca;
    }

    public TipoItemConsumo getTipoItemConsumo() {
        return tipoItemConsumo;
    }

    public void setTipoItemConsumo(TipoItemConsumo tipoItemConsumo) {
        this.tipoItemConsumo = tipoItemConsumo;
    }

    public TipoBebida getTipoBebida() {
        return tipoBebida;
    }

    public void setTipoBebida(TipoBebida tipoBebida) {
        this.tipoBebida = tipoBebida;
    }

    public CardapioModel getCardapio() {
        return cardapio;
    }

public void setCardapio(CardapioModel cardapio) {
        this.cardapio = cardapio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemConsumoModel that = (ItemConsumoModel) o;
        return Objects.equals(id, that.id) && Objects.equals(descricao, that.descricao) && Objects.equals(preco, that.preco) && estadoItemPedido == that.estadoItemPedido && Objects.equals(dataCriacao, that.dataCriacao) && Objects.equals(cozinha, that.cozinha) && Objects.equals(origem, that.origem) && Objects.equals(dataActualizaca, that.dataActualizaca) && tipoItemConsumo == that.tipoItemConsumo && tipoBebida == that.tipoBebida && Objects.equals(cardapio, that.cardapio);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, descricao, preco, estadoItemPedido, dataCriacao, cozinha, origem, dataActualizaca, tipoItemConsumo, tipoBebida, cardapio);
        result = 31 * result;
        return result;
    }

    @Override
    public String toString() {
        return "ItemConsumoModel{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", estadoItemPedido=" + estadoItemPedido +
                ", dataCriacao=" + dataCriacao +
                ", cozinha='" + cozinha + '\'' +
                ", origem='" + origem + '\'' +
                ", dataActualizaca=" + dataActualizaca +
                ", tipoItemConsumo=" + tipoItemConsumo +
                ", tipoBebida=" + tipoBebida +
                ", cardapioModel=" + cardapio +
                '}';
    }
}
