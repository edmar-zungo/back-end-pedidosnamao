package com.edmarzungo.pedidosnamao.services.implementacions;

import com.edmarzungo.pedidosnamao.domain.ItemConsumoModel;
import com.edmarzungo.pedidosnamao.exceptions.GlobalExeception;
import com.edmarzungo.pedidosnamao.repositories.ItemConsumoRepository;
import com.edmarzungo.pedidosnamao.services.ItemConsumoService;
import com.edmarzungo.pedidosnamao.services.dtos.ItemConsumoDTO;
import com.edmarzungo.pedidosnamao.services.mappers.ItemConsumoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemConsumoServiceImpl implements ItemConsumoService {

    private final ItemConsumoRepository itemConsumoRepository;
    private final ItemConsumoMapper itemConsumoMapper;

    public ItemConsumoServiceImpl(ItemConsumoRepository itemConsumoRepository, ItemConsumoMapper itemConsumoMapper) {
        this.itemConsumoRepository = itemConsumoRepository;
        this.itemConsumoMapper = itemConsumoMapper;
    }

    @Override
    public ItemConsumoDTO save(ItemConsumoDTO itemConsumoDTO) {
        ItemConsumoModel itemConsumoModel = itemConsumoMapper.itemConsumoDTOToItemConsumoModel(itemConsumoDTO);
        itemConsumoModel = itemConsumoRepository.save(itemConsumoModel);
        itemConsumoDTO = itemConsumoMapper.itemConsumoModelToItemConsumoDTO(itemConsumoModel);
        return itemConsumoDTO;

    }

    @Override
    public ItemConsumoDTO update(ItemConsumoDTO itemConsumoDTO, UUID itemConsumoId) {
        ItemConsumoModel ietmConsumoToUpdate = itemConsumoRepository.findById(itemConsumoId).orElseThrow(() -> new GlobalExeception("Nenhum Item de Consumo encontrado!"));

        ietmConsumoToUpdate.setCardapio(itemConsumoDTO.cardapio());
        ietmConsumoToUpdate.setdataActualizacao(ZonedDateTime.now());
        ietmConsumoToUpdate.setTipoItemConsumo(itemConsumoDTO.tipoItemConsumo());
        ietmConsumoToUpdate.setCozinha(itemConsumoDTO.cozinha());
        ietmConsumoToUpdate.setDescricao(itemConsumoDTO.descricao());
        ietmConsumoToUpdate.setEstadoItemPedido(itemConsumoDTO.estadoItemPedido());
        ietmConsumoToUpdate.setItemPedido(itemConsumoDTO.itemPedido());
        ietmConsumoToUpdate.setImagem(itemConsumoDTO.imagem());
        ietmConsumoToUpdate.setOrigem(itemConsumoDTO.origem());
        ietmConsumoToUpdate.setPreco(itemConsumoDTO.preco());
        ietmConsumoToUpdate.setTipoBebida(itemConsumoDTO.tipoBebida());

        ietmConsumoToUpdate = itemConsumoRepository.save(ietmConsumoToUpdate);
        itemConsumoDTO = itemConsumoMapper.itemConsumoModelToItemConsumoDTO(ietmConsumoToUpdate);

        return itemConsumoDTO;
    }

    @Override
    public List<ItemConsumoDTO> getAll() {
        return itemConsumoRepository.findAll()
                .stream()
                .map(itemConsumoMapper::itemConsumoModelToItemConsumoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ItemConsumoDTO getOne(UUID itemConsumoId) {
        Optional<ItemConsumoModel> itemConsumoResult = itemConsumoRepository.findById(itemConsumoId);

        if (itemConsumoResult.isEmpty()){
            throw new GlobalExeception("Nenhum Item de Consumo encontrado!");

        }

        ItemConsumoDTO itemConsumoDTO = itemConsumoMapper.itemConsumoModelToItemConsumoDTO(itemConsumoResult.get());
        return itemConsumoDTO;
    }

    @Override
    public void delete(UUID itemConsumoId) {
        itemConsumoRepository.deleteById(itemConsumoId);
    }
}
