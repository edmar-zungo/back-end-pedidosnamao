package com.edmarzungo.pedidosnamao.domain;

import com.edmarzungo.pedidosnamao.enumerations.EstadoItem;
import com.edmarzungo.pedidosnamao.enumerations.TipoBebida;
import com.edmarzungo.pedidosnamao.enumerations.TipoItemConsumo;
import com.edmarzungo.pedidosnamao.enumerations.TipoPrato;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Lob
    private byte[] imagem;

    private String imagemContentType;

    @NotBlank
    private String descricao;
    @NotNull
    private Double preco;
    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoItem estadoItemConsumo;
    @NotNull
    private ZonedDateTime dataCriacao;

    private String cozinha;

    private String origem;
    @NotNull
    private ZonedDateTime dataActualizacao;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoItemConsumo tipoItemConsumo;

    @Enumerated(EnumType.STRING)
    private TipoPrato tipoPrato;

    @Enumerated(EnumType.STRING)
    private TipoBebida tipoBebida;
    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cardapio_id")
    private CardapioModel cardapio;


    public ItemConsumoModel() {
    }

    public ItemConsumoModel(UUID id, byte[] imagem, String imagemContentType, String descricao, Double preco, EstadoItem estadoItemConsumo, ZonedDateTime dataCriacao, String cozinha, String origem, ZonedDateTime dataActualizacao, TipoItemConsumo tipoItemConsumo, TipoPrato tipoPrato, TipoBebida tipoBebida, CardapioModel cardapio) {
        this.id = id;
        this.imagem = imagem;
        this.imagemContentType = imagemContentType;
        this.descricao = descricao;
        this.preco = preco;
        this.estadoItemConsumo = estadoItemConsumo;
        this.dataCriacao = dataCriacao;
        this.cozinha = cozinha;
        this.origem = origem;
        this.dataActualizacao = dataActualizacao;
        this.tipoItemConsumo = tipoItemConsumo;
        this.tipoPrato = tipoPrato;
        this.tipoBebida = tipoBebida;
        this.cardapio = cardapio;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public ItemConsumoModel imagem(byte[] imagem){
        this.setImagem(imagem);
        return this;
    }

    public String getImagemContentType() {
        return imagemContentType;
    }

    public void setImagemContentType(String imagemContentType) {
        this.imagemContentType = imagemContentType;
    }

    public ItemConsumoModel imagemContentType(String imagemContentType){
        this.setImagemContentType(imagemContentType);
        return this;
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

    public EstadoItem getEstadoItemConsumo() {
        return estadoItemConsumo;
    }

    public void setEstadoItemConsumo(EstadoItem estadoItemConsumo) {
        this.estadoItemConsumo = estadoItemConsumo;
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

    public ZonedDateTime getdataActualizacao() {
        return dataActualizacao;
    }

    public void setdataActualizacao(ZonedDateTime dataActualizacao) {
        this.dataActualizacao = dataActualizacao;
    }

    public TipoItemConsumo getTipoItemConsumo() {
        return tipoItemConsumo;
    }

    public void setTipoItemConsumo(TipoItemConsumo tipoItemConsumo) {
        this.tipoItemConsumo = tipoItemConsumo;
    }

    public TipoPrato getTipoPrato() {
        return tipoPrato;
    }

    public void setTipoPrato(TipoPrato tipoPrato) {
        this.tipoPrato = tipoPrato;
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
        return Objects.equals(id, that.id) && Arrays.equals(imagem, that.imagem) && Objects.equals(imagemContentType, that.imagemContentType) && Objects.equals(descricao, that.descricao) && Objects.equals(preco, that.preco) && estadoItemConsumo == that.estadoItemConsumo && Objects.equals(dataCriacao, that.dataCriacao) && Objects.equals(cozinha, that.cozinha) && Objects.equals(origem, that.origem) && Objects.equals(dataActualizacao, that.dataActualizacao) && tipoItemConsumo == that.tipoItemConsumo && tipoPrato == that.tipoPrato && tipoBebida == that.tipoBebida && Objects.equals(cardapio, that.cardapio);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, Arrays.hashCode(imagem),imagemContentType,descricao, preco, estadoItemConsumo, dataCriacao, cozinha, origem, dataActualizacao, tipoItemConsumo,tipoPrato, tipoBebida, cardapio);
        result = 31 * result;
        return result;
    }

    @Override
    public String toString() {
        return "ItemConsumoModel{" +
                "id=" + id +
                "imagem=" + Arrays.toString(imagem) +
                "imagemContentType=" + imagemContentType +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", estadoItemConsumo=" + estadoItemConsumo +
                ", dataCriacao=" + dataCriacao +
                ", cozinha='" + cozinha + '\'' +
                ", origem='" + origem + '\'' +
                ", dataActualizacao=" + dataActualizacao +
                ", tipoItemConsumo=" + tipoItemConsumo +
                ", tipoPrato=" + tipoPrato +
                ", tipoBebida=" + tipoBebida +
                ", cardapioModel=" + cardapio +
                '}';
    }



}
