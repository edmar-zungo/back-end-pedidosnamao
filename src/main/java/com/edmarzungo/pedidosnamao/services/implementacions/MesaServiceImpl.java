package com.edmarzungo.pedidosnamao.services.implementacions;

import com.edmarzungo.pedidosnamao.domain.CardapioModel;
import com.edmarzungo.pedidosnamao.domain.MesaModel;
import com.edmarzungo.pedidosnamao.enumerations.EstadoItem;
import com.edmarzungo.pedidosnamao.exceptions.GlobalExeception;
import com.edmarzungo.pedidosnamao.repositories.MesaRepository;
import com.edmarzungo.pedidosnamao.services.MesaService;
import com.edmarzungo.pedidosnamao.services.dtos.CardapioDTO;
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
    final Long ZERO = 0L;

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
        MesaModel mesaToUpdate = mesaRepository.findById(id).orElseThrow(() -> new GlobalExeception("Nenhuma mesa encontrada!"));

        if (!mesa.numero().equals(mesaToUpdate.getNumero())){
            if (existeNumero(mesa.numero())){
                throw new GlobalExeception("Já existe uma mesa com esse número: " + mesa.numero());
            }

            mesaToUpdate.setNumero(mesa.numero());
            mesaToUpdate.setDescricao(gerarDescricao(mesaMapper.mesaDTOToMesaModel(mesa)));
        }

        if (!mesa.descricao().equals(mesaToUpdate.getDescricao())){
            mesaToUpdate.setDescricao(mesa.descricao());
        }

        if (!mesa.quantidadeLugares().equals(mesaToUpdate.getQuantidadeLugares())){
            mesaToUpdate.setQuantidadeLugares(mesa.quantidadeLugares());
            mesaToUpdate.setDescricao(gerarDescricao(mesaMapper.mesaDTOToMesaModel(mesa)));
        }

            mesaToUpdate.setSequencia(mesa.sequencia());
            mesaToUpdate.setEstadoMesa(mesa.estadoMesa());

            mesaToUpdate = mesaRepository.save(mesaToUpdate);

            mesa = mesaMapper.mesaToMesaDTO( mesaToUpdate );

       return mesa;

    }

    @Override
    public List<MesaDTO> getAll() {
        return mesaRepository.findAll()
                .stream()
                .map(mesaMapper::mesaToMesaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MesaDTO getOne(UUID mesaId) {
        Optional<MesaModel> mesaResult = mesaRepository.findById(mesaId);
        if (mesaResult.isEmpty()){
            throw new GlobalExeception("Nenhuma Mesa encontrado!");
        }

        MesaDTO mesaDTO = mesaMapper.mesaToMesaDTO(mesaResult.get());

        return mesaDTO;
    }

    @Override
    public void delete(UUID mesaId) {
        mesaRepository.deleteById(mesaId);
    }

    @Override
    public long gerarSequencia() {
        return (long) mesaRepository
                .findAll()
                .size() + 1;
    }

    @Override
    public MesaDTO init(MesaDTO mesaDTO) {
        MesaModel mesaModel = mesaMapper.mesaDTOToMesaModel(mesaDTO);


        mesaModel.setSequencia(mesaModel.getSequencia() == null ? gerarSequencia() : mesaModel.getSequencia());

        mesaModel.setNumero(geraNumero(mesaModel.getSequencia().toString()));
        mesaModel.setEstadoMesa( mesaModel.getEstadoMesa() == null ? EstadoItem.DISPONIVEL : mesaModel.getEstadoMesa() );
       if (mesaModel.getQuantidadeLugares() == null || mesaModel.getQuantidadeLugares() == ZERO){
           mesaModel.setQuantidadeLugares(ONE);
       }else{
           mesaModel.setQuantidadeLugares(mesaModel.getQuantidadeLugares());
       }
        mesaModel.setDescricao( gerarDescricao(mesaModel) );

        return mesaMapper.mesaToMesaDTO(mesaModel);
    }



    private String gerarDescricao(MesaModel mesaModel){
        String descricao = " Mesa - " + mesaModel.getNumero() + " de ";

        if (ONE.equals(mesaModel.getQuantidadeLugares())){
            descricao += mesaModel.getQuantidadeLugares() + " lugar";

        } else{
            descricao += mesaModel.getQuantidadeLugares() + " lugares";
        }

        return descricao;
    }

    private Boolean existeSequencia(Long sequencia){
        return mesaRepository.findAll()
                .stream()
                .anyMatch(x -> x.getSequencia().equals(sequencia));
    }

    private Boolean existeNumero(String numero){
        return mesaRepository.findAll()
                .stream()
                .anyMatch(x -> x.getNumero().equals(numero));
    }

    private String geraNumero(String numero){
        numero = "MS/" + numero;
        if (existeNumero(numero)){
            throw new GlobalExeception("Já existe uma mesa com esse número: " + numero);
        }
        return numero;
    }
}
