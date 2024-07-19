package com.edmarzungo.pedidosnamao.services.implementacions;

import com.edmarzungo.pedidosnamao.domain.PedidoModel;
import com.edmarzungo.pedidosnamao.enumerations.EstadoItem;
import com.edmarzungo.pedidosnamao.enumerations.EstadoPedido;
import com.edmarzungo.pedidosnamao.exceptions.GlobalExeception;
import com.edmarzungo.pedidosnamao.repositories.PedidoRepository;
import com.edmarzungo.pedidosnamao.services.PedidoService;
import com.edmarzungo.pedidosnamao.services.dtos.PedidoDTO;
import com.edmarzungo.pedidosnamao.services.mappers.PedidoMapper;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;

    final Double ZERO = 0.0d;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, PedidoMapper pedidoMapper) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    public PedidoDTO save(PedidoDTO pedidoDTO) {
        PedidoModel pedidoModel = pedidoMapper.pedidoDTOToPedidoModel(pedidoDTO);
        pedidoModel = pedidoRepository.save(pedidoModel);
        pedidoDTO = pedidoMapper.pedidoToPedidoDTO(pedidoModel);
        return pedidoDTO;
    }

    @Override
    public PedidoDTO update(PedidoDTO pedidoDTO, UUID pedidoId) {
        PedidoModel pedidoToUpdate = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new GlobalExeception("Nenhum Pedido encontrado!"));

        pedidoToUpdate.setDescricao(pedidoDTO.descricao());
        pedidoToUpdate.setEstadoPedido(pedidoDTO.estadoPedido());
        pedidoToUpdate.setSequencia(pedidoDTO.sequencia());
        pedidoToUpdate.setNumero(pedidoDTO.numero());
        pedidoToUpdate.setDataActualizacao(LocalDateTime.now());
        pedidoToUpdate.setDataCriacao(pedidoDTO.dataCriacao());
        pedidoToUpdate.setDeliver(pedidoDTO.isDeliver());
        pedidoToUpdate.setDescricaoEntrega(pedidoDTO.descricaoEntrega());
        pedidoToUpdate.setEnderecoDetalhado(pedidoDTO.enderecoDetalhado());
        pedidoToUpdate.setMesa(pedidoDTO.mesa());
        pedidoToUpdate.setTempoEntrega(pedidoDTO.tempoEntrega());
        pedidoToUpdate.setTotalPagar(pedidoDTO.totalPagar());
        pedidoToUpdate.setTotalPago(pedidoDTO.totalPago());
        pedidoToUpdate.setTotalTroco(pedidoDTO.totalTroco());
        pedidoToUpdate.setValorEntrega(pedidoDTO.valorEntrega());

        pedidoToUpdate = pedidoRepository.save(pedidoToUpdate);
        pedidoDTO = pedidoMapper.pedidoToPedidoDTO(pedidoToUpdate);

        return pedidoDTO;
    }

    @Override
    public List<PedidoDTO> getAll() {
        return pedidoRepository.findAll()
                .stream()
                .map(pedidoMapper::pedidoToPedidoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PedidoDTO getOne(UUID pedidoId) {
        Optional<PedidoModel> pedidoResult = pedidoRepository.findById(pedidoId);

        if (pedidoResult.isEmpty()){
            throw new GlobalExeception("Nenhum Pedido encontrado!");
        }

        PedidoDTO pedidoDTO = pedidoMapper.pedidoToPedidoDTO(pedidoResult.get());

        return pedidoDTO;
    }

    @Override
    public void delete(UUID pedidoId) {
        pedidoRepository.deleteById(pedidoId);
    }

    @Override
    public PedidoDTO init(PedidoDTO pedidoDTO) {
        PedidoModel pedidoModel = pedidoMapper.pedidoDTOToPedidoModel(pedidoDTO);
        if (pedidoModel.getSequencia() != null && existeSequencia(pedidoModel.getSequencia())){
            throw new GlobalExeception("Sequência existente!");
        }
        if (pedidoModel.getNumero() != null && existeNumero(pedidoModel.getNumero())){
            throw new GlobalExeception("Já existe um Pedido com existente número - " + pedidoModel.getNumero() + "!");
        }

        pedidoModel.setSequencia(pedidoModel.getSequencia() == null ? getSequencia() : pedidoModel.getSequencia() );
        pedidoModel.setNumero(pedidoModel.getNumero() == null ? gerarNumero(pedidoDTO) : pedidoModel.getNumero());
        pedidoModel.setEstadoPedido( pedidoModel.getEstadoPedido() == null ? EstadoPedido.PENDENTE : pedidoModel.getEstadoPedido() );
        pedidoModel.setDataCriacao( pedidoModel.getDataCriacao() == null ? LocalDateTime.now() : pedidoModel.getDataCriacao() );
        pedidoModel.setDataActualizacao( pedidoModel.getDataActualizacao() == null ? LocalDateTime.now() : pedidoModel.getDataActualizacao() );
        pedidoModel.setDeliver( pedidoModel.getIsDeliver() == null ? false : pedidoModel.getIsDeliver() );

        if (pedidoModel.getIsDeliver().equals(false) && pedidoModel.getMesa() == null){
            throw new GlobalExeception("Adicione uma mesa!");
        }

        if (pedidoModel.getIsDeliver().equals(false) && (pedidoModel.getMesa().getEstadoMesa().equals(EstadoItem.RESERVADO) || pedidoModel.getMesa().getEstadoMesa().equals(EstadoItem.INDISPONIVEL))){
            throw new GlobalExeception("A mesa escolhida encontra-se indisponível no momento!");
        }

        pedidoModel.setValorEntrega(pedidoDTO.valorEntrega() == null ?  ZERO : pedidoDTO.valorEntrega());
        pedidoModel.setTotalPagar(pedidoModel.getTotalPagar() == null ? ZERO : pedidoModel.getTotalPagar() );
        pedidoModel.setTotalPago(pedidoDTO.totalPago() == null ? ZERO : pedidoModel.getTotalPago() );
        pedidoModel.setTotalTroco(pedidoModel.getTotalTroco() == null ? ZERO : pedidoModel.getTotalTroco() );

        return pedidoDTO;
    }

    private Long getSequencia(){
        return pedidoRepository
                .findAll()
                .stream()
                .count() + 1;
    }

    private Boolean existeSequencia(Long sequencia){
        return pedidoRepository.findAll()
                .stream()
                .anyMatch(x -> x.getSequencia().equals(sequencia));
    }

    private String gerarNumero(PedidoDTO pedidoDTO){
        int ano = pedidoDTO.dataCriacao().getYear();
        long sequencia = pedidoDTO.sequencia();
        String numero = "PD/" + sequencia + "/" + ano;

        return numero;
    }

    private Boolean existeNumero(String numero){
        return pedidoRepository.findAll()
                .stream()
                .anyMatch(x -> x.getNumero().equals(numero));
    }
}
