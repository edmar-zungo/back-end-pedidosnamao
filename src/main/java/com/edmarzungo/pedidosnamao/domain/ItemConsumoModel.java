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
@Table(name = "item_consumo")
public class ItemConsumoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;


    @Column(name = "imagem")
    @Lob
    private byte[] imagem;

    @Column(name = "imagem_content_type")
    private String imagemContentType;

    @Column(name = "descricao")
    @NotBlank
    private String descricao;
    @NotNull
    @Column(name = "preco")
    private Double preco;
    @NotNull
    @Column(name = "estado_item_pedido")
    @Enumerated(EnumType.STRING)
    private EstadoItem estadoItemPedido;
    @NotNull
    @Column(name = "data_criacao")
    private ZonedDateTime dataCriacao;

    @Column(name = "cozinha")
    private String cozinha;

    @Column(name = "origem")
    private String origem;
    @NotNull
    @Column(name = "data_actualizacao")
    private ZonedDateTime dataActualizaca;
    @NotNull
    @Column(name = "tipo_item_consumo")
    @Enumerated(EnumType.STRING)
    private TipoItemConsumo tipoItemConsumo;

    @Column(name = "tipo_bebida")
    @Enumerated(EnumType.STRING)
    private TipoBebida tipoBebida;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cardapio_id")
    private CardapioModel cardapioModel;

    public ItemConsumoModel() {
    }

    public ItemConsumoModel(UUID id, byte[] imagem, String imagemContentType, String descricao, Double preco, EstadoItem estadoItemPedido, ZonedDateTime dataCriacao, String cozinha, String origem, ZonedDateTime dataActualizaca, TipoItemConsumo tipoItemConsumo, TipoBebida tipoBebida, CardapioModel cardapioModel) {
        this.id = id;
        this.imagem = imagem;
        this.imagemContentType = imagemContentType;
        this.descricao = descricao;
        this.preco = preco;
        this.estadoItemPedido = estadoItemPedido;
        this.dataCriacao = dataCriacao;
        this.cozinha = cozinha;
        this.origem = origem;
        this.dataActualizaca = dataActualizaca;
        this.tipoItemConsumo = tipoItemConsumo;
        this.tipoBebida = tipoBebida;
        this.cardapioModel = cardapioModel;
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

    public String getImagemContentType() {
        return imagemContentType;
    }

    public void setImagemContentType(String imagemContentType) {
        this.imagemContentType = imagemContentType;
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

    public CardapioModel getCardapioModel() {
        return cardapioModel;
    }

    public void setCardapioModel(CardapioModel cardapioModel) {
        this.cardapioModel = cardapioModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemConsumoModel that = (ItemConsumoModel) o;
        return Objects.equals(id, that.id) && Arrays.equals(imagem, that.imagem) && Objects.equals(imagemContentType, that.imagemContentType) && Objects.equals(descricao, that.descricao) && Objects.equals(preco, that.preco) && estadoItemPedido == that.estadoItemPedido && Objects.equals(dataCriacao, that.dataCriacao) && Objects.equals(cozinha, that.cozinha) && Objects.equals(origem, that.origem) && Objects.equals(dataActualizaca, that.dataActualizaca) && tipoItemConsumo == that.tipoItemConsumo && tipoBebida == that.tipoBebida && Objects.equals(cardapioModel, that.cardapioModel);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, imagemContentType, descricao, preco, estadoItemPedido, dataCriacao, cozinha, origem, dataActualizaca, tipoItemConsumo, tipoBebida, cardapioModel);
        result = 31 * result + Arrays.hashCode(imagem);
        return result;
    }

    @Override
    public String toString() {
        return "ItemConsumoModel{" +
                "id=" + id +
                ", imagem=" + Arrays.toString(imagem) +
                ", imagemContentType='" + imagemContentType + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", estadoItemPedido=" + estadoItemPedido +
                ", dataCriacao=" + dataCriacao +
                ", cozinha='" + cozinha + '\'' +
                ", origem='" + origem + '\'' +
                ", dataActualizaca=" + dataActualizaca +
                ", tipoItemConsumo=" + tipoItemConsumo +
                ", tipoBebida=" + tipoBebida +
                ", cardapioModel=" + cardapioModel +
                '}';
    }
}
