package com.edmarzungo.pedidosnamao.domain;

import com.edmarzungo.pedidosnamao.enumerations.EstadoPedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private String numero;
    @NotNull
    private LocalDateTime dataActualizacao;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mesa_id")
    private MesaModel mesa;
    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoPedido estadoPedido;

    private String descricao;
    private Boolean isDeliver;
    private String enderecoEntregaDetalhado;
    private LocalTime tempoEntrega;
    private String descricaoEntrega;
    private Double valorEntrega;

    private Double totalPagar;
    private Double totalPago;
    private Double totalTroco;

    public PedidoModel() {
    }

    public PedidoModel(UUID id, LocalDateTime dataCriacao, Long sequencia, String numero, LocalDateTime dataActualizacao, MesaModel mesa, EstadoPedido estadoPedido, String descricao, Boolean isDeliver, String enderecoEntregaDetalhado, LocalTime tempoEntrega, String descricaoEntrega, Double valorEntrega, Double totalPagar, Double totalPago, Double totalTroco, Set<ItemPedidoModel> itensPedido) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.sequencia = sequencia;
        this.numero = numero;
        this.dataActualizacao = dataActualizacao;
        this.mesa = mesa;
        this.estadoPedido = estadoPedido;
        this.descricao = descricao;
        this.isDeliver = isDeliver;
        this.enderecoEntregaDetalhado = enderecoEntregaDetalhado;
        this.tempoEntrega = tempoEntrega;
        this.descricaoEntrega = descricaoEntrega;
        this.valorEntrega = valorEntrega;
        this.totalPagar = totalPagar;
        this.totalPago = totalPago;
        this.totalTroco = totalTroco;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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
        this.isDeliver = isDeliver;
    }

    public String getEnderecoEntregaDetalhado() {
        return enderecoEntregaDetalhado;
    }

    public void setEnderecoEntregaDetalhado(String enderecoEntregaDetalhado) {
        this.enderecoEntregaDetalhado = enderecoEntregaDetalhado;
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




    // Método para adicionar um item de consumo
//    public void addItemConsumo(ItemPedidoModel itemPedido) {
//        itensPedido.add(itemPedido);
//        itemPedido.setPedido(this);
//    }
//
//    // Método para remover um item de consumo
//    public void removeItemConsumo(ItemPedidoModel itemPedido) {
//        itensPedido.remove(itemPedido);
//        itemPedido.setPedido(null);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoModel that = (PedidoModel) o;
        return isDeliver == that.isDeliver && Objects.equals(id, that.id) && Objects.equals(dataCriacao, that.dataCriacao) && Objects.equals(sequencia, that.sequencia) && Objects.equals(numero, that.numero) && Objects.equals(dataActualizacao, that.dataActualizacao)&& Objects.equals(mesa, that.mesa) && estadoPedido == that.estadoPedido && Objects.equals(descricao, that.descricao) && Objects.equals(enderecoEntregaDetalhado, that.enderecoEntregaDetalhado) && Objects.equals(tempoEntrega, that.tempoEntrega) && Objects.equals(descricaoEntrega, that.descricaoEntrega) && Objects.equals(valorEntrega, that.valorEntrega) && Objects.equals(totalPagar, that.totalPagar) && Objects.equals(totalPago, that.totalPago) && Objects.equals(totalTroco, that.totalTroco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataCriacao, sequencia, numero,dataActualizacao, mesa, estadoPedido, descricao, isDeliver, enderecoEntregaDetalhado, tempoEntrega, descricaoEntrega, valorEntrega, totalPagar, totalPago, totalTroco);
    }

    @Override
    public String toString() {
        return "PedidoModel{" +
                "id=" + id +
                ", dataCriacao=" + dataCriacao +
                "sequencia=" + sequencia +
                "numero=" + numero +
                ", dataActualizacao=" + dataActualizacao +
                ", mesaModel=" + mesa +
                ", estadoPedido=" + estadoPedido +
                ", descricao='" + descricao + '\'' +
                ", getIsDeliver=" + isDeliver +
                ", enderecoEntregaDetalhado='" + enderecoEntregaDetalhado + '\'' +
                ", tempoEntrega=" + tempoEntrega +
                ", descricaoEntrega='" + descricaoEntrega + '\'' +
                ", valorEntrega=" + valorEntrega +
                ", totalPagar=" + totalPagar +
                ", totalPago=" + totalPago +
                ", totalTroco=" + totalTroco +
                '}';
    }
}
