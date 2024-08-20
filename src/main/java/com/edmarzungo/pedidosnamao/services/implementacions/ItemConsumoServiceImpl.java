package com.edmarzungo.pedidosnamao.services.implementacions;

import com.edmarzungo.pedidosnamao.domain.ItemConsumoModel;
import com.edmarzungo.pedidosnamao.enumerations.EstadoItem;
import com.edmarzungo.pedidosnamao.enumerations.TipoItemConsumo;
import com.edmarzungo.pedidosnamao.exceptions.GlobalExeception;
import com.edmarzungo.pedidosnamao.repositories.ItemConsumoRepository;
import com.edmarzungo.pedidosnamao.services.ItemConsumoService;
import com.edmarzungo.pedidosnamao.services.dtos.ItemConsumoDTO;
import com.edmarzungo.pedidosnamao.services.mappers.ItemConsumoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

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
        itemConsumoDTO = init(itemConsumoDTO);
        ItemConsumoModel itemConsumoModel = itemConsumoMapper.itemConsumoDTOToItemConsumoModel(itemConsumoDTO);

        itemConsumoModel = itemConsumoRepository.save(itemConsumoModel);
        itemConsumoDTO = itemConsumoMapper.itemConsumoModelToItemConsumoDTO(itemConsumoModel);
        return itemConsumoDTO;

    }

    @Override
    public ItemConsumoDTO update(ItemConsumoDTO itemConsumoDTO, UUID itemConsumoId) {
        ItemConsumoModel itemConsumoToUpdate = itemConsumoRepository.findById(itemConsumoId).orElseThrow(() -> new GlobalExeception("Nenhum Item de Consumo encontrado!"));

        itemConsumoToUpdate.setdataActualizacao(ZonedDateTime.now());
        itemConsumoToUpdate.setCardapio(itemConsumoDTO.cardapio());
        itemConsumoToUpdate.setdataActualizacao(ZonedDateTime.now());
        itemConsumoToUpdate.setTipoItemConsumo(itemConsumoDTO.tipoItemConsumo());
        itemConsumoToUpdate.setCozinha(itemConsumoDTO.cozinha());
        itemConsumoToUpdate.setDescricao(itemConsumoDTO.descricao());
        itemConsumoToUpdate.setEstadoItemPedido(itemConsumoDTO.estadoItemPedido());
        itemConsumoToUpdate.setImagem(itemConsumoDTO.imagem());
        itemConsumoToUpdate.setImagemContentType(itemConsumoDTO.imagemContentType());
        itemConsumoToUpdate.setOrigem(itemConsumoDTO.origem());
        itemConsumoToUpdate.setPreco(itemConsumoDTO.preco());
        itemConsumoToUpdate.setTipoBebida(itemConsumoDTO.tipoBebida());

        itemConsumoToUpdate = itemConsumoRepository.save(itemConsumoToUpdate);
        itemConsumoDTO = itemConsumoMapper.itemConsumoModelToItemConsumoDTO(itemConsumoToUpdate);

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

        if (itemConsumoResult.isEmpty()) {
            throw new GlobalExeception("Nenhum Item de Consumo encontrado!");

        }

        ItemConsumoDTO itemConsumoDTO = itemConsumoMapper.itemConsumoModelToItemConsumoDTO(itemConsumoResult.get());
        return itemConsumoDTO;
    }

    @Override
    public void delete(UUID itemConsumoId) {
        itemConsumoRepository.deleteById(itemConsumoId);
    }

    private ItemConsumoDTO init(ItemConsumoDTO itemConsumoDTO) {
        ItemConsumoModel itemConsumoModel = itemConsumoMapper.itemConsumoDTOToItemConsumoModel(itemConsumoDTO);

        if (itemConsumoModel.getPreco() == null) {
            throw new GlobalExeception("Deve especificar um preço para este item!");
        }

        if (itemConsumoModel.getTipoItemConsumo() == null) {
            throw new GlobalExeception("Deve especificar o tipo especifico deste item de consumo! Ex. Comida ou Bebida");
        }

        if (itemConsumoModel.getTipoItemConsumo().equals(TipoItemConsumo.PRATO)) {
            if (itemConsumoModel.getTipoPrato() == null) {
                throw new GlobalExeception("Deve especificar que tipo de prato especifico é. Ex. Principal, Sobre-mesa e Entrada.");
            }
            if (itemConsumoModel.getCozinha() == null) {
                throw new GlobalExeception("Deve especificar a cozinha a qual pertence esse prato! Ex. Cozinha Italiana, Cozinha Angolana, etc.");
            }

        }

        if (itemConsumoModel.getTipoItemConsumo().equals(TipoItemConsumo.BEBIDA)) {
            if (itemConsumoModel.getTipoBebida() == null) {
                throw new GlobalExeception("Deve especificar o tipo de bebida. Ex. Alcoolica ou Não Alcoolica.");
            }
            if (itemConsumoModel.getOrigem() == null) {
                throw new GlobalExeception("Deve especificar a origem dessa bebida. Ex. Alemanhã, Escócia,etc.");
            }

        }

        if (itemConsumoModel.getCardapio() == null) {
            throw new GlobalExeception("Item não vinculado a nenhum cardápio, por favor adicione o item a um cardápio.");
        }

        itemConsumoModel.setDataCriacao(ZonedDateTime.now());
        itemConsumoModel.setdataActualizacao(ZonedDateTime.now());
        itemConsumoModel.setEstadoItemPedido(itemConsumoModel.getEstadoItemPedido() == null ? EstadoItem.DISPONIVEL : itemConsumoModel.getEstadoItemPedido());


        itemConsumoDTO = itemConsumoMapper.itemConsumoModelToItemConsumoDTO(itemConsumoModel);
        return itemConsumoDTO;
    }
}
