package com.edmarzungo.pedidosnamao.services.implementacions;

import com.edmarzungo.pedidosnamao.domain.ItemPedidoModel;
import com.edmarzungo.pedidosnamao.domain.MesaModel;
import com.edmarzungo.pedidosnamao.exceptions.GlobalExeception;
import com.edmarzungo.pedidosnamao.repositories.ItemPedidoRepository;
import com.edmarzungo.pedidosnamao.services.ItemPedidoService;
import com.edmarzungo.pedidosnamao.services.dtos.ItemPedidoDTO;
import com.edmarzungo.pedidosnamao.services.dtos.MesaDTO;
import com.edmarzungo.pedidosnamao.services.mappers.ItemPedidoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemPedidoServiceImpl implements ItemPedidoService {

    private final ItemPedidoMapper itemPedidoMapper;
    private final ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoServiceImpl(ItemPedidoMapper itemPedidoMapper, ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoMapper = itemPedidoMapper;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Override
    public ItemPedidoDTO save(ItemPedidoDTO itemPedidoDTO) {
        itemPedidoDTO = init(itemPedidoDTO);
        ItemPedidoModel itemPedidoModel = itemPedidoMapper.itemPedidoDTOToItemPedidoModel(itemPedidoDTO);

        itemPedidoModel = itemPedidoRepository.save(itemPedidoModel);

        itemPedidoDTO = itemPedidoMapper.itemPedidoModelToItemPedidoDTO(itemPedidoModel);

        return itemPedidoDTO;
    }

    @Override
    public ItemPedidoDTO update(ItemPedidoDTO itemPedidoDTO, UUID itemPedidoId) {

        ItemPedidoModel itemPedidoModel = itemPedidoRepository.findById(itemPedidoId).orElseThrow(() -> new GlobalExeception("Nenhum item encontrado!"));

        itemPedidoModel.setQuantidadeItemConsumo(itemPedidoDTO.quantidadeItemConsumo());
        itemPedidoModel.setPrecoItemPedido(itemPedidoDTO.precoItemPedido());
        itemPedidoModel.setDesconto(itemPedidoDTO.desconto());
        itemPedidoModel.setItemConsumo(itemPedidoDTO.itemConsumo());
        itemPedidoModel.setDescricao(itemPedidoDTO.descricao());

        itemPedidoModel = itemPedidoRepository.save(itemPedidoModel);
        itemPedidoDTO = itemPedidoMapper.itemPedidoModelToItemPedidoDTO(itemPedidoModel);

        return itemPedidoDTO;
    }

    @Override
    public List<ItemPedidoDTO> getAll() {
        return itemPedidoRepository
                .findAll()
                .stream()
                .map(itemPedidoMapper::itemPedidoModelToItemPedidoDTO)
                .toList();
    }

    @Override
    public ItemPedidoDTO getOne(UUID itemPedidoId) {
        Optional<ItemPedidoModel> itemPedidoResult = itemPedidoRepository.findById(itemPedidoId);
        if (itemPedidoResult.isEmpty()){
            throw new GlobalExeception("Nenhuma item encontrado!");
        }

        ItemPedidoDTO itemPedidoDTO = itemPedidoMapper.itemPedidoModelToItemPedidoDTO(itemPedidoResult.get());

        return itemPedidoDTO;
    }

    @Override
    public void delete(UUID itemPedidoId) {
        itemPedidoRepository.deleteById(itemPedidoId);
    }

    @Override
    public ItemPedidoDTO init(ItemPedidoDTO itemPedidoDTO) {
        ItemPedidoModel itemPedidoModel = itemPedidoMapper.itemPedidoDTOToItemPedidoModel(itemPedidoDTO);

        if (itemPedidoModel.getPedido().getId() == null){
            throw new GlobalExeception("Adicione um pedido ao item de consumo!");
        }

        if (itemPedidoModel.getItemConsumo() == null){
            throw new GlobalExeception("Adicione um item de consumo!");
        }

        if (itemPedidoModel.getPrecoItemPedido() == null){
            throw new GlobalExeception("Adicione um preço ao item!");
        }

        itemPedidoModel.setQuantidadeItemConsumo(itemPedidoModel.getQuantidadeItemConsumo() == null ? 1L : itemPedidoModel.getQuantidadeItemConsumo() );

        itemPedidoModel.setDesconto(itemPedidoModel.getDesconto() == null ? 0D : itemPedidoModel.getDesconto());

        if (itemPedidoModel.getDesconto() > 0){
            itemPedidoModel.setPrecoItemPedido(itemPedidoModel.getPrecoItemPedido() - itemPedidoModel.getDesconto() );
        }

        itemPedidoDTO = itemPedidoMapper.itemPedidoModelToItemPedidoDTO(itemPedidoModel);


        return itemPedidoDTO;
    }
}
