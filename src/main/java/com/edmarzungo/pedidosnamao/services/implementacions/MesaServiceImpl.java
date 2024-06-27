package com.edmarzungo.pedidosnamao.services.implementacions;

import com.edmarzungo.pedidosnamao.domain.MesaModel;
import com.edmarzungo.pedidosnamao.enumerations.EstadoItem;
import com.edmarzungo.pedidosnamao.exceptions.GlobalExeception;
import com.edmarzungo.pedidosnamao.repositories.MesaRepository;
import com.edmarzungo.pedidosnamao.services.MesaService;
import com.edmarzungo.pedidosnamao.services.dtos.MesaDTO;
import com.edmarzungo.pedidosnamao.services.mappers.MesaMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class MesaServiceImpl implements MesaService {

    private final MesaRepository mesaRepository;
    private final MesaMapper mesaMapper;
    final Long ONE = 1L;

    public MesaServiceImpl(MesaRepository mesaRepository, MesaMapper mesaMapper) {
        this.mesaRepository = mesaRepository;
        this.mesaMapper = mesaMapper;
    }

    @Override
    public MesaDTO save(MesaDTO mesaDTO) {

        mesaDTO = init(mesaDTO);
        MesaModel mesaSaved = mesaRepository.save( mesaMapper.mesaDTOToMesaModel(mesaDTO));
        return mesaMapper.mesaToMesaDTO(mesaSaved);
    }

    @Override
    public MesaDTO update(MesaDTO mesa, UUID id) {
        MesaModel mesaToUpdate = mesaRepository.findById(id).orElseThrow();
        mesaToUpdate.setSequencia(mesa.sequencia());
        mesaToUpdate.setNumero(mesa.numero());
        mesaToUpdate.setDescricao(mesa.descricao());
        mesaToUpdate.setEstadoMesa(mesa.estadoMesa());

       return mesaMapper.mesaToMesaDTO( mesaRepository.save(mesaToUpdate) );

    }

    @Override
    public List<MesaDTO> getAll() {
        return mesaRepository.findAll()
                .stream()
                .map(mesaMapper::mesaToMesaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MesaDTO> getOne(UUID mesaId) {
        return mesaRepository.findById(mesaId).map(mesaMapper::mesaToMesaDTO);
    }

    @Override
    public void delete(UUID mesaId) {
        mesaRepository.deleteById(mesaId);
    }

    @Override
    public Long gerarSequencia() {
        return mesaRepository
                .findAll()
                .stream()
                .count() + 1;
    }

    @Override
    public MesaDTO init(MesaDTO mesaDTO) {
        MesaModel mesaModel = mesaMapper.mesaDTOToMesaModel(mesaDTO);
        if (mesaModel.getSequencia() != null && existeSequencia(mesaModel.getSequencia())){
            throw new GlobalExeception("Sequência existente!");
        }
        if (mesaModel.getNumero() != null && existeNumero(mesaModel.getNumero())){
            throw new GlobalExeception("Já existe uma mesa com esse número " + mesaModel.getNumero());
        }

        mesaModel.setSequencia(mesaModel.getSequencia() == null ? this.gerarSequencia() : mesaModel.getSequencia() );
        mesaModel.setNumero(mesaModel.getNumero() == null ? mesaModel.getSequencia() : mesaModel.getNumero() );
        mesaModel.setEstadoMesa( mesaModel.getEstadoMesa() == null ? EstadoItem.DISPONIVEL : mesaModel.getEstadoMesa() );
        mesaModel.setQuantidadeLugares(mesaModel.getQuantidadeLugares() == null ? ONE : mesaModel.getQuantidadeLugares() );
        mesaModel.setDescricao( gerarDescricao(mesaModel) );

        return mesaMapper.mesaToMesaDTO(mesaModel);
    }



    private String gerarDescricao(MesaModel mesaModel){
        String descricao = "M" + mesaModel.getNumero() + "-";

        if (mesaModel.getQuantidadeLugares() > ONE){
            descricao += mesaModel.getQuantidadeLugares() + "lugares";
        } else{
            descricao += mesaModel.getQuantidadeLugares() + "lugar";
        }

        return descricao;
    }

    private Boolean existeSequencia(Long sequencia){
        return mesaRepository.findAll()
                .stream()
                .anyMatch(x -> x.getSequencia().equals(sequencia));
    }

    private Boolean existeNumero(Long numero){
        return mesaRepository.findAll()
                .stream()
                .anyMatch(x -> x.getNumero().equals(numero));
    }
}
