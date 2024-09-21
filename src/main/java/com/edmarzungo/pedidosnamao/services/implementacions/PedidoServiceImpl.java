package com.edmarzungo.pedidosnamao.services.implementacions;

import com.edmarzungo.pedidosnamao.domain.PedidoModel;
import com.edmarzungo.pedidosnamao.enumerations.EstadoItem;
import com.edmarzungo.pedidosnamao.enumerations.EstadoPedido;
import com.edmarzungo.pedidosnamao.exceptions.GlobalExeception;
import com.edmarzungo.pedidosnamao.repositories.PedidoRepository;
import com.edmarzungo.pedidosnamao.services.PedidoService;
import com.edmarzungo.pedidosnamao.services.dtos.PedidoDTO;
import com.edmarzungo.pedidosnamao.services.mappers.PedidoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@Transactional
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
        pedidoToUpdate.setDeliver(pedidoDTO.deliver());
        pedidoToUpdate.setDescricaoEntrega(pedidoDTO.descricaoEntrega());
        pedidoToUpdate.setEnderecoEntregaDetalhado(pedidoDTO.enderecoEntregaDetalhado());
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
    public PedidoDTO init() {

        PedidoModel novoPedido = new PedidoModel();

        var sequencia = gerarSequencia();
        var numeroPedido = gerarNumero(sequencia);
        var estadoPendente = EstadoPedido.PENDENTE;
        var dataCriacao = LocalDateTime.now();
        var dataActualizacao = LocalDateTime.now();

        novoPedido.setSequencia(sequencia);
        novoPedido.setNumero(numeroPedido);
        novoPedido.setEstadoPedido(estadoPendente);
        novoPedido.setDataCriacao(dataCriacao);
        novoPedido.setDataActualizacao(dataActualizacao);

//
//        if (pedidoModel.getSequencia() != null && existeSequencia(pedidoModel.getSequencia())){
//            throw new GlobalExeception("Sequência existente!");
//        }
//
//        if (pedidoModel.getNumero() != null && existeNumero(pedidoModel.getNumero())){
//            throw new GlobalExeception("Já existe um Pedido com este número - " + pedidoModel.getNumero() + "!");
//        }
//        pedidoModel.setEstadoPedido( pedidoModel.getEstadoPedido() == null ? EstadoPedido.PENDENTE : pedidoModel.getEstadoPedido() );
//        pedidoModel.setDataCriacao( pedidoModel.getDataCriacao() == null ? LocalDateTime.now() : pedidoModel.getDataCriacao() );
//        pedidoModel.setDataActualizacao( pedidoModel.getDataActualizacao() == null ? LocalDateTime.now() : pedidoModel.getDataActualizacao() );
//        pedidoModel.setDeliver( pedidoModel.isDeliver() == null ? false : pedidoModel.isDeliver() );
//
//        if ((!pedidoModel.isDeliver()) && pedidoModel.getMesa() == null){
//            throw new GlobalExeception("Adicione uma mesa!");
//        }
//
//        if (pedidoModel.isDeliver().equals(false) && (pedidoModel.getMesa().getEstadoMesa().equals(EstadoItem.RESERVADO) || pedidoModel.getMesa().getEstadoMesa().equals(EstadoItem.INDISPONIVEL))){
//            throw new GlobalExeception("A mesa escolhida encontra-se indisponível no momento!");
//        }
//
//        pedidoModel.setTotalPagar(pedidoModel.getTotalPagar() == null ? ZERO : pedidoModel.getTotalPagar());
//
//        if (pedidoModel.isDeliver().equals(true)){
//            if (pedidoModel.getEnderecoEntregaDetalhado() == null){
//                throw new GlobalExeception("Adicione o endereço de entrega, de forma detalhada.");
//            }
//
//            pedidoModel.setTempoEntrega(LocalTime.of(1, 20));
//            pedidoModel.setDescricaoEntrega( geraDesdcricaoPedido(pedidoModel) );
//
//
//            pedidoModel.setValorEntrega(pedidoModel.getValorEntrega() == null ?  ZERO : pedidoModel.getValorEntrega());
//            pedidoModel.setTotalPagar( totalPagar(pedidoModel.getTotalPagar(), pedidoModel.getValorEntrega()) );
//        }
//
//        pedidoModel.setTotalPago(pedidoModel.getTotalPago() == null ? ZERO : pedidoModel.getTotalPago() );
//        pedidoModel.setTotalTroco(pedidoModel.getTotalTroco() == null ? ZERO : pedidoModel.getTotalTroco() );
//
//        pedidoModel.setDescricao(geraDesdcricaoPedido(pedidoModel));

        PedidoDTO pedidoDTO = pedidoMapper.pedidoToPedidoDTO(novoPedido);
        pedidoDTO = save(pedidoDTO);



        return pedidoDTO;
    }

    @Override
    public PedidoDTO mudarEstadoPedido(PedidoDTO pedidoDTO) {
        pedidoDTO = getOne(pedidoDTO.id());
        PedidoModel pedidoModel = pedidoMapper.pedidoDTOToPedidoModel(pedidoDTO);
        pedidoModel.setEstadoPedido(EstadoPedido.CONCLUIDO);

        pedidoModel = pedidoRepository.save(pedidoModel);

        pedidoDTO = pedidoMapper.pedidoToPedidoDTO(pedidoModel);
        return pedidoDTO;
    }

    private Long gerarSequencia(){
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

    private String gerarNumero(long sequencia){

        return "PD/" + sequencia;
    }

    private Boolean existeNumero(String numero){
        return pedidoRepository.findAll()
                .stream()
                .anyMatch(x -> x.getNumero().equals(numero));
    }

    private String geraDesdcricaoPedido(PedidoModel pedidoModel){
        String descricao = "";

        if (pedidoModel.isDeliver()){
            descricao = "Pedido de entrega número: " + pedidoModel.getNumero() + ", para o endereço: " + pedidoModel.getEnderecoEntregaDetalhado() + ", na data e hora: " + pedidoModel.getDataActualizacao() + ", tempo de entrega: " + pedidoModel.getTempoEntrega().getHour() +":" + pedidoModel.getTempoEntrega().getMinute() + "min" + ", no valor de: " + pedidoModel.getTotalPagar();

            return descricao;
        }

        descricao = "Pedido número: " + pedidoModel.getNumero() + " para a " + pedidoModel.getMesa().getDescricao();

        return descricao;
    }
    private Double totalPagar(Double valorPagar, Double valorEntrega){
        return valorPagar + valorEntrega;
    }
}
