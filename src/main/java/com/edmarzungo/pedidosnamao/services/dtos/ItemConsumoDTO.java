package com.edmarzungo.pedidosnamao.services.dtos;
import com.edmarzungo.pedidosnamao.domain.CardapioModel;
import com.edmarzungo.pedidosnamao.enumerations.EstadoItem;
import com.edmarzungo.pedidosnamao.enumerations.TipoBebida;
import com.edmarzungo.pedidosnamao.enumerations.TipoItemConsumo;
import com.edmarzungo.pedidosnamao.enumerations.TipoPrato;

import java.time.ZonedDateTime;
import java.util.UUID;

public class ItemConsumoDTO {
    private UUID id;
    private byte[] imagem;
    private String imagemContentType;
    private String descricao;
    private Double preco;
    private EstadoItem estadoItemConsumo;
    private ZonedDateTime dataCriacao;
    private String cozinha;
    private String origem;
    private ZonedDateTime dataActualizacao;
    private TipoItemConsumo tipoItemConsumo;
    private TipoPrato tipoPrato;
    private TipoBebida tipoBebida;
    private CardapioModel cardapio;
    private Double desconto;

    public ItemConsumoDTO(UUID id, byte[] imagem, String imagemContentType, String descricao, Double preco,
                          EstadoItem estadoItemConsumo, ZonedDateTime dataCriacao, String cozinha, String origem,
                          ZonedDateTime dataActualizacao, TipoItemConsumo tipoItemConsumo, TipoPrato tipoPrato,
                          TipoBebida tipoBebida, CardapioModel cardapio, Double desconto) {
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
        this.desconto = desconto;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public byte[] getImagem() { return imagem; }
    public void setImagem(byte[] imagem) { this.imagem = imagem; }

    public String getImagemContentType() { return imagemContentType; }
    public void setImagemContentType(String imagemContentType) { this.imagemContentType = imagemContentType; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }

    public EstadoItem getEstadoItemConsumo() { return estadoItemConsumo; }
    public void setEstadoItemConsumo(EstadoItem estadoItemConsumo) { this.estadoItemConsumo = estadoItemConsumo; }

    public ZonedDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(ZonedDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public String getCozinha() { return cozinha; }
    public void setCozinha(String cozinha) { this.cozinha = cozinha; }

    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }

    public ZonedDateTime getDataActualizacao() { return dataActualizacao; }
    public void setDataActualizacao(ZonedDateTime dataActualizacao) { this.dataActualizacao = dataActualizacao; }

    public TipoItemConsumo getTipoItemConsumo() { return tipoItemConsumo; }
    public void setTipoItemConsumo(TipoItemConsumo tipoItemConsumo) { this.tipoItemConsumo = tipoItemConsumo; }

    public TipoPrato getTipoPrato() { return tipoPrato; }
    public void setTipoPrato(TipoPrato tipoPrato) { this.tipoPrato = tipoPrato; }

    public TipoBebida getTipoBebida() { return tipoBebida; }
    public void setTipoBebida(TipoBebida tipoBebida) { this.tipoBebida = tipoBebida; }

    public CardapioModel getCardapio() { return cardapio; }
    public void setCardapio(CardapioModel cardapio) { this.cardapio = cardapio; }

    public Double getDesconto() { return desconto; }
    public void setDesconto(Double desconto) { this.desconto = desconto; }
}
