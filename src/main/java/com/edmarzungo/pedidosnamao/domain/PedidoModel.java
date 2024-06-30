package com.edmarzungo.pedidosnamao.domain;

import com.edmarzungo.pedidosnamao.enumerations.EstadoPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    private LocalDateTime dataCriacao;
    @NotNull
    private Long sequencia;
    @NotNull
    private LocalDateTime dataActualizacao;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mesa_id")
    private MesaModel mesa;
    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoPedido estadoPedido;
    private String descricao;
    private Boolean isDeliver;
    private String enderecoDetalhado;
    private LocalTime tempoEntrega;
    private String descricaoEntrega;
    private Double valorEntrega;

    private Double totalPagar;
    private Double totalPago;
    private Double totalTroco;

    @NotNull
    @OneToMany
    @JoinColumn(name = "item_pedido_id")
    private Set<ItemPedidoModel> itensPedido;

    public PedidoModel() {
    }

    public PedidoModel(UUID id, LocalDateTime dataCriacao, Long sequencia, LocalDateTime dataActualizacao, MesaModel mesa, EstadoPedido estadoPedido, String descricao, Boolean isDeliver, String enderecoDetalhado, LocalTime tempoEntrega, String descricaoEntrega, Double valorEntrega, Double totalPagar, Double totalPago, Double totalTroco, Set<ItemPedidoModel> itensPedido) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.sequencia = sequencia;
        this.dataActualizacao = dataActualizacao;
        this.mesa = mesa;
        this.estadoPedido = estadoPedido;
        this.descricao = descricao;
        this.isDeliver = isDeliver;
        this.enderecoDetalhado = enderecoDetalhado;
        this.tempoEntrega = tempoEntrega;
        this.descricaoEntrega = descricaoEntrega;
        this.valorEntrega = valorEntrega;
        this.totalPagar = totalPagar;
        this.totalPago = totalPago;
        this.totalTroco = totalTroco;
        this.itensPedido = itensPedido;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataActualizacao() {
        return dataActualizacao;
    }

    public void setDataActualizacao(LocalDateTime dataActualizacao) {
        this.dataActualizacao = dataActualizacao;
    }
    public Long getSequencia() {
        return sequencia;
    }

    public void setSequencia(Long sequencia) {
        this.sequencia = sequencia;
    }

    public MesaModel getMesa() {
        return mesa;
    }

    public void setMesa(MesaModel mesa) {
        this.mesa = mesa;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean isDeliver() {
        return isDeliver;
    }

    public void setDeliver(Boolean isDeliver) {
        isDeliver = isDeliver;
    }

    public String getEnderecoDetalhado() {
        return enderecoDetalhado;
    }

    public void setEnderecoDetalhado(String enderecoDetalhado) {
        this.enderecoDetalhado = enderecoDetalhado;
    }

    public LocalTime getTempoEntrega() {
        return tempoEntrega;
    }

    public void setTempoEntrega(LocalTime tempoEntrega) {
        this.tempoEntrega = tempoEntrega;
    }

    public String getDescricaoEntrega() {
        return descricaoEntrega;
    }

    public void setDescricaoEntrega(String descricaoEntrega) {
        this.descricaoEntrega = descricaoEntrega;
    }

    public Double getValorEntrega() {
        return valorEntrega;
    }

    public void setValorEntrega(Double valorEntrega) {
        this.valorEntrega = valorEntrega;
    }

    public Double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public Double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(Double totalPago) {
        this.totalPago = totalPago;
    }

    public Double getTotalTroco() {
        return totalTroco;
    }

    public void setTotalTroco(Double totalTroco) {
        this.totalTroco = totalTroco;
    }


    public Set<ItemPedidoModel> getItemPedido() {
        return itensPedido;
    }

    public void setItemPedido(Set<ItemPedidoModel> itemPedido) {
        this.itensPedido = itemPedido;
    }


    // Método para adicionar um item de consumo
    public void addItemConsumo(ItemPedidoModel itemPedido) {
        itensPedido.add(itemPedido);
        itemPedido.setPedido(this);
    }

    // Método para remover um item de consumo
    public void removeItemConsumo(ItemPedidoModel itemPedido) {
        itensPedido.remove(itemPedido);
        itemPedido.setPedido(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoModel that = (PedidoModel) o;
        return isDeliver == that.isDeliver && Objects.equals(id, that.id) && Objects.equals(dataCriacao, that.dataCriacao) && Objects.equals(sequencia, that.sequencia) && Objects.equals(dataActualizacao, that.dataActualizacao)&& Objects.equals(mesa, that.mesa) && estadoPedido == that.estadoPedido && Objects.equals(descricao, that.descricao) && Objects.equals(enderecoDetalhado, that.enderecoDetalhado) && Objects.equals(tempoEntrega, that.tempoEntrega) && Objects.equals(descricaoEntrega, that.descricaoEntrega) && Objects.equals(valorEntrega, that.valorEntrega) && Objects.equals(totalPagar, that.totalPagar) && Objects.equals(totalPago, that.totalPago) && Objects.equals(totalTroco, that.totalTroco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataCriacao, sequencia,dataActualizacao, mesa, estadoPedido, descricao, isDeliver, enderecoDetalhado, tempoEntrega, descricaoEntrega, valorEntrega, totalPagar, totalPago, totalTroco);
    }

    @Override
    public String toString() {
        return "PedidoModel{" +
                "id=" + id +
                ", dataCriacao=" + dataCriacao +
                "sequencia=" + sequencia +
                ", dataActualizacao=" + dataActualizacao +
                ", mesaModel=" + mesa +
                ", estadoPedido=" + estadoPedido +
                ", descricao='" + descricao + '\'' +
                ", isDeliver=" + isDeliver +
                ", enderecoDetalhado='" + enderecoDetalhado + '\'' +
                ", tempoEntrega=" + tempoEntrega +
                ", descricaoEntrega='" + descricaoEntrega + '\'' +
                ", valorEntrega=" + valorEntrega +
                ", totalPagar=" + totalPagar +
                ", totalPago=" + totalPago +
                ", totalTroco=" + totalTroco +
                '}';
    }


}
