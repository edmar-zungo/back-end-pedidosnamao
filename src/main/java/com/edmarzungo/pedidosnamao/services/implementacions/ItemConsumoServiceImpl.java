package com.edmarzungo.pedidosnamao.services.implementacions;

import com.edmarzungo.pedidosnamao.domain.ItemConsumoModel;
import com.edmarzungo.pedidosnamao.enumerations.EstadoItem;
import com.edmarzungo.pedidosnamao.enumerations.TipoCardapio;
import com.edmarzungo.pedidosnamao.enumerations.TipoItemConsumo;
import com.edmarzungo.pedidosnamao.exceptions.GlobalExeception;
import com.edmarzungo.pedidosnamao.repositories.ItemConsumoRepository;
import com.edmarzungo.pedidosnamao.services.ItemConsumoService;
import com.edmarzungo.pedidosnamao.services.dtos.ItemConsumoDTO;
import com.edmarzungo.pedidosnamao.services.dtos.ItemPedidoDTO;
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
        itemConsumoToUpdate.setCardapio(itemConsumoDTO.getCardapio());
        itemConsumoToUpdate.setdataActualizacao(ZonedDateTime.now());
        itemConsumoToUpdate.setTipoItemConsumo(itemConsumoDTO.getTipoItemConsumo());
        itemConsumoToUpdate.setCozinha(itemConsumoDTO.getCozinha());
        itemConsumoToUpdate.setDescricao(itemConsumoDTO.getDescricao());
        itemConsumoToUpdate.setEstadoItemConsumo(itemConsumoDTO.getEstadoItemConsumo());
        itemConsumoToUpdate.setImagem(itemConsumoDTO.getImagem());
        itemConsumoToUpdate.setImagemContentType(itemConsumoDTO.getImagemContentType());
        itemConsumoToUpdate.setOrigem(itemConsumoDTO.getOrigem());
        itemConsumoToUpdate.setPreco(itemConsumoDTO.getPreco());
        itemConsumoToUpdate.setTipoBebida(itemConsumoDTO.getTipoBebida());

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
    public List<ItemConsumoDTO> getItensConsumoByCardapio(UUID cardapioId) {
        return itemConsumoRepository.findAll()
                .stream()
                .filter(x -> x.getCardapio().getId().equals(cardapioId))
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

        if (itemConsumoModel.getCardapio() == null) {
            throw new GlobalExeception("Item não vinculado a nenhum cardápio, por favor adicione o item a um cardápio.");
        }

        if (itemConsumoModel.getTipoItemConsumo() == null) {
            throw new GlobalExeception("Deve especificar o tipo especifico deste item! Ex. Prato ou Bebida");
        }

        if (itemConsumoModel.getPreco() == null) {
            throw new GlobalExeception("Deve especificar um preço para este item! Ex. 500 kz");
        }

        if (itemConsumoModel.getCardapio().getTipoCardapio().equals(TipoCardapio.PRATOS) && itemConsumoModel.getTipoItemConsumo().equals(TipoItemConsumo.BEBIDA)) {
            throw new GlobalExeception("Não pode adicionar uma Bebida em um cardápio de Pratos. Verifique o tipo do item a ser adicionado!");
        }

        if (itemConsumoModel.getCardapio().getTipoCardapio().equals(TipoCardapio.BEBIDAS) && itemConsumoModel.getTipoItemConsumo().equals(TipoItemConsumo.PRATO)) {
            throw new GlobalExeception("Não pode adicionar um Prato em um cardápio de Bebidas. Verifique o tipo do item a ser adicionado!");
        }

        if (itemConsumoModel.getTipoItemConsumo().equals(TipoItemConsumo.PRATO) && itemConsumoModel.getTipoPrato() == null) {
            throw new GlobalExeception("Deve especificar que tipo de prato. Ex. Principal, Sobre-mesa e Entrada.");
        }

        if (itemConsumoModel.getTipoItemConsumo().equals(TipoItemConsumo.BEBIDA) && itemConsumoModel.getTipoBebida() == null) {
            throw new GlobalExeception("Deve especificar o tipo de bebida. Ex. Álcoolica ou Não Álcoolica.");
        }


        itemConsumoModel.setOrigem(itemConsumoModel.getOrigem() == null ? "" : itemConsumoModel.getOrigem());
        itemConsumoModel.setCozinha(itemConsumoModel.getCozinha() == null ? "" : itemConsumoModel.getCozinha());
        itemConsumoModel.setDataCriacao(ZonedDateTime.now());
        itemConsumoModel.setdataActualizacao(ZonedDateTime.now());
        itemConsumoModel.setEstadoItemConsumo(itemConsumoModel.getEstadoItemConsumo() == null ? EstadoItem.DISPONIVEL : itemConsumoModel.getEstadoItemConsumo());


        itemConsumoDTO = itemConsumoMapper.itemConsumoModelToItemConsumoDTO(itemConsumoModel);
        return itemConsumoDTO;
    }
}
