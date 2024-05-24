package com.edmarzungo.pedidosnamao.domain;

import com.edmarzungo.pedidosnamao.enumerations.EstadoItem;
import com.edmarzungo.pedidosnamao.enumerations.TipoBebida;
import com.edmarzungo.pedidosnamao.enumerations.TipoItemConsumo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class ItemConsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private byte[] imagem;

    @NotBlank
    private String descricao;
    @NotBlank
    private Double preco;
    @NotNull
    private EstadoItem estadoItemPedido;
    @NotNull
    private LocalDateTime dataCriacao;

    private String cozinha;
    private String origem;
    @NotNull
    private LocalDateTime dataActualizaca;
    @NotNull
    private TipoItemConsumo tipoItemConsumo;
    private TipoBebida tipoBebida;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Set<Cardapio> cardapios;

    public ItemConsumo() {
    }

    public ItemConsumo(UUID id, byte[] imagem, String descricao, Double preco, EstadoItem estadoItemPedido, LocalDateTime dataCriacao, String cozinha, String origem, LocalDateTime dataActualizaca, TipoItemConsumo tipoItemConsumo, TipoBebida tipoBebida, Set<Cardapio> cardapios) {
        this.id = id;
        this.imagem = imagem;
        this.descricao = descricao;
        this.preco = preco;
        this.estadoItemPedido = estadoItemPedido;
        this.dataCriacao = dataCriacao;
        this.cozinha = cozinha;
        this.origem = origem;
        this.dataActualizaca = dataActualizaca;
        this.tipoItemConsumo = tipoItemConsumo;
        this.tipoBebida = tipoBebida;
        this.cardapios = cardapios;
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
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

    public LocalDateTime getDataActualizaca() {
        return dataActualizaca;
    }

    public void setDataActualizaca(LocalDateTime dataActualizaca) {
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

    public Set<Cardapio> getCardapios() {
        return cardapios;
    }

    public void setCardapios(Set<Cardapio> cardapios) {
        this.cardapios = cardapios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemConsumo that = (ItemConsumo) o;
        return Objects.equals(id, that.id) && Arrays.equals(imagem, that.imagem) && Objects.equals(descricao, that.descricao) && Objects.equals(preco, that.preco) && estadoItemPedido == that.estadoItemPedido && Objects.equals(dataCriacao, that.dataCriacao) && Objects.equals(cozinha, that.cozinha) && Objects.equals(origem, that.origem) && Objects.equals(dataActualizaca, that.dataActualizaca) && tipoItemConsumo == that.tipoItemConsumo && tipoBebida == that.tipoBebida && Objects.equals(cardapios, that.cardapios);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, descricao, preco, estadoItemPedido, dataCriacao, cozinha, origem, dataActualizaca, tipoItemConsumo, tipoBebida, cardapios);
        result = 31 * result + Arrays.hashCode(imagem);
        return result;
    }

    @Override
    public String toString() {
        return "ItemConsumo{" +
                "id=" + id +
                ", imagem=" + Arrays.toString(imagem) +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", estadoItemPedido=" + estadoItemPedido +
                ", dataCriacao=" + dataCriacao +
                ", cozinha='" + cozinha + '\'' +
                ", origem='" + origem + '\'' +
                ", dataActualizaca=" + dataActualizaca +
                ", tipoItemConsumo=" + tipoItemConsumo +
                ", tipoBebida=" + tipoBebida +
                ", cardapios=" + cardapios +
                '}';
    }
}
