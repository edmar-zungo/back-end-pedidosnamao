package com.edmarzungo.pedidosnamao.services.implementacions;

import com.edmarzungo.pedidosnamao.domain.PedidoModel;
import com.edmarzungo.pedidosnamao.enumerations.EstadoItem;
import com.edmarzungo.pedidosnamao.enumerations.EstadoPedido;
import com.edmarzungo.pedidosnamao.exceptions.GlobalExeception;
import com.edmarzungo.pedidosnamao.repositories.PedidoRepository;
import com.edmarzungo.pedidosnamao.services.ItemPedidoService;
import com.edmarzungo.pedidosnamao.services.PedidoService;
import com.edmarzungo.pedidosnamao.services.dtos.ItemPedidoDTO;
import com.edmarzungo.pedidosnamao.services.dtos.PedidoDTO;
import com.edmarzungo.pedidosnamao.services.dtos.PedidoPageDTO;
import com.edmarzungo.pedidosnamao.services.mappers.ItemPedidoMapper;
import com.edmarzungo.pedidosnamao.services.mappers.PedidoMapper;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@Transactional
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final ItemPedidoService itemPedidoService;
    private final ItemPedidoMapper itemPedidoMapper;

    final Double ZERO = 0.0d;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, PedidoMapper pedidoMapper, ItemPedidoService itemPedidoService, ItemPedidoMapper itemPedidoMapper) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoMapper = pedidoMapper;
        this.itemPedidoService = itemPedidoService;
        this.itemPedidoMapper = itemPedidoMapper;
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

        List<ItemPedidoDTO> itemPedidoDTOList = itemPedidoService.getAll().stream().filter(x -> x.pedido().getId().equals(pedidoId)).toList();

        pedidoToUpdate.setMesa(pedidoDTO.mesa());
        pedidoToUpdate.setDescricao(geraDesdcricaoPedido(pedidoToUpdate));
        pedidoToUpdate.setDataActualizacao(LocalDateTime.now());
        pedidoToUpdate.setDeliver(pedidoDTO.deliver());

        if (pedidoToUpdate.isDeliver().equals(false)){
            if (pedidoToUpdate.getMesa() == null){
                throw new GlobalExeception("Adicione uma mesa!");
            }

            if ((pedidoToUpdate.getMesa().getEstadoMesa().equals(EstadoItem.RESERVADO) || pedidoToUpdate.getMesa().getEstadoMesa().equals(EstadoItem.INDISPONIVEL))){
                throw new GlobalExeception("A mesa escolhida encontra-se indisponível no momento!");
            }
        }

        if (pedidoToUpdate.isDeliver().equals(true)){
            if (pedidoToUpdate.getEnderecoEntregaDetalhado() == null){
                throw new GlobalExeception("Adicione o endereço de entrega, de forma detalhada.");
            }

            pedidoToUpdate.setEnderecoEntregaDetalhado(pedidoDTO.enderecoEntregaDetalhado());
            pedidoToUpdate.setTempoEntrega(LocalTime.of(1, 20));
            pedidoToUpdate.setDescricaoEntrega( geraDesdcricaoPedido(pedidoToUpdate) );
            pedidoToUpdate.setValorEntrega(ZERO);
        }

        if (itemPedidoDTOList.isEmpty()){
            throw new GlobalExeception("Adicione itens ao pedido!");
        }

        pedidoToUpdate.setTotalPagar(ZERO);
        pedidoToUpdate.setTotalPago(ZERO);
        pedidoToUpdate.setTotalTroco(ZERO);
        pedidoToUpdate.setEstadoPedido(EstadoPedido.CONFIRMADO);

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
    public PedidoPageDTO getAllPageble(int pageNumber, int pageItens) {

        Sort sort = Sort.by("dataCriacao").descending();

        Page<PedidoDTO> pedidoDTOPage = pedidoRepository.findAll(PageRequest.of(pageNumber, pageItens, sort)).map(pedidoMapper::pedidoToPedidoDTO);
        List<PedidoDTO> pedidoDTOList = pedidoDTOPage.getContent();
        var elementoPage = pedidoDTOPage.getTotalElements();
        var paginasReturn = pedidoDTOPage.getTotalPages();
        return new PedidoPageDTO(pedidoDTOList,elementoPage, paginasReturn);
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
        novoPedido.setDeliver(false);

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

//
        novoPedido.setValorEntrega(ZERO);
        novoPedido.setTotalPago(ZERO);
        novoPedido.setTotalTroco(ZERO);

        novoPedido.setDescricao(geraDesdcricaoPedido(novoPedido));

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
        DateTimeFormatter dateTimeFormatterDefault = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");

        if (pedidoModel.isDeliver()){
            descricao = "Pedido de entrega número: " + pedidoModel.getNumero() + ", feito aos: " + pedidoModel.getDataCriacao() + ", para o endereço: " + pedidoModel.getEnderecoEntregaDetalhado() + " tempo aproximado de entrega: " + pedidoModel.getTempoEntrega().format(dateTimeFormatterDefault);

            return descricao;
        }

        if(pedidoModel.getMesa() != null){
            descricao = "Pedido número: " + pedidoModel.getNumero() + ", feito aos: " + pedidoModel.getDataCriacao().format(dateTimeFormatterDefault) + " para a " + pedidoModel.getMesa().getDescricao();

            return descricao;
        }

        descricao = "Pedido número: " + pedidoModel.getNumero() + ", feito aos: " + pedidoModel.getDataCriacao().format(dateTimeFormatterDefault);

        return descricao;
    }
    private Double totalPagar(Double valorPagar, Double valorEntrega){
        return valorPagar + valorEntrega;
    }

    @Override
    public PedidoDTO calculaTotalPagar(UUID pedidoId){
        PedidoDTO pedidoDTO = getOne(pedidoId);
        PedidoModel pedidoModel = pedidoMapper.pedidoDTOToPedidoModel(pedidoDTO);
        List<ItemPedidoDTO> itemPedidoDTOList = itemPedidoService.getAll().stream().filter(x -> x.pedido().getId().equals(pedidoModel.getId())).toList();
        Double totalPagar = ZERO;

        for (var item: itemPedidoDTOList){
            totalPagar += item.precoItemPedido();
        }

        pedidoModel.setTotalPagar(totalPagar);
        pedidoDTO = pedidoMapper.pedidoToPedidoDTO(pedidoModel);

        return pedidoDTO;

    }
}
