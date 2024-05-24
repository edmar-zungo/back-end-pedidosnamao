package com.edmarzungo.pedidosnamao.domain;

import com.edmarzungo.pedidosnamao.enumerations.EstadoPedido;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Timer;
import java.util.UUID;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private LocalDateTime dataCriacao;
    @NotNull
    private LocalDateTime dataActualizacao;
    private Mesa mesa;
    @NotNull
    private EstadoPedido estadoPedido;
    private String descricao;
    private boolean isDeliver;
    private String enderecoDetalhado;
    private LocalTime tempoEntrega;
    private String descricaoEntrega;
    private Double valorEntrega;

    private Double totalPagar;

    public Pedido() {
    }

    public Pedido(UUID id, LocalDateTime dataCriacao, LocalDateTime dataActualizacao, Mesa mesa, EstadoPedido estadoPedido, String descricao, boolean isDeliver, String enderecoDetalhado, LocalTime tempoEntrega, String descricaoEntrega, Double valorEntrega, Double totalPagar) {
        this.id = id;
        this.dataCriacao = dataCriacao;
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

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
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

    public boolean isDeliver() {
        return isDeliver;
    }

    public void setDeliver(boolean deliver) {
        isDeliver = deliver;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return isDeliver == pedido.isDeliver && Objects.equals(id, pedido.id) && Objects.equals(dataCriacao, pedido.dataCriacao) && Objects.equals(dataActualizacao, pedido.dataActualizacao) && Objects.equals(mesa, pedido.mesa) && estadoPedido == pedido.estadoPedido && Objects.equals(descricao, pedido.descricao) && Objects.equals(enderecoDetalhado, pedido.enderecoDetalhado) && Objects.equals(tempoEntrega, pedido.tempoEntrega) && Objects.equals(descricaoEntrega, pedido.descricaoEntrega) && Objects.equals(valorEntrega, pedido.valorEntrega) && Objects.equals(totalPagar, pedido.totalPagar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataCriacao, dataActualizacao, mesa, estadoPedido, descricao, isDeliver, enderecoDetalhado, tempoEntrega, descricaoEntrega, valorEntrega, totalPagar);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", dataCriacao=" + dataCriacao +
                ", dataActualizacao=" + dataActualizacao +
                ", mesa=" + mesa +
                ", estadoPedido=" + estadoPedido +
                ", descricao='" + descricao + '\'' +
                ", isDeliver=" + isDeliver +
                ", enderecoDetalhado='" + enderecoDetalhado + '\'' +
                ", tempoEntrega=" + tempoEntrega +
                ", descricaoEntrega='" + descricaoEntrega + '\'' +
                ", valorEntrega=" + valorEntrega +
                ", totalPagar=" + totalPagar +
                '}';
    }
}
