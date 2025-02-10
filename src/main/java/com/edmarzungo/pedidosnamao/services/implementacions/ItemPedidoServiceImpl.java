package com.edmarzungo.pedidosnamao.services.implementacions;

import com.edmarzungo.pedidosnamao.domain.ItemPedidoModel;
import com.edmarzungo.pedidosnamao.domain.MesaModel;
import com.edmarzungo.pedidosnamao.exceptions.GlobalExeception;
import com.edmarzungo.pedidosnamao.repositories.ItemPedidoRepository;
import com.edmarzungo.pedidosnamao.services.ItemPedidoService;
import com.edmarzungo.pedidosnamao.services.dtos.ItemConsumoDTO;
import com.edmarzungo.pedidosnamao.services.dtos.ItemPedidoDTO;
import com.edmarzungo.pedidosnamao.services.dtos.MesaDTO;
import com.edmarzungo.pedidosnamao.services.mappers.ItemConsumoMapper;
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
    private final ItemConsumoMapper itemConsumoMapper;

    public ItemPedidoServiceImpl(ItemPedidoMapper itemPedidoMapper, ItemPedidoRepository itemPedidoRepository, ItemConsumoMapper itemConsumoMapper) {
        this.itemPedidoMapper = itemPedidoMapper;
        this.itemPedidoRepository = itemPedidoRepository;
        this.itemConsumoMapper = itemConsumoMapper;
    }

    @Override
    public ItemPedidoDTO save(ItemPedidoDTO itemPedidoDTO) {

        ItemPedidoModel itemPedidoModel = itemPedidoMapper.itemPedidoDTOToItemPedidoModel(itemPedidoDTO);

        itemPedidoModel = itemPedidoRepository.save(itemPedidoModel);

        itemPedidoDTO = itemPedidoMapper.itemPedidoModelToItemPedidoDTO(itemPedidoModel);

        return itemPedidoDTO;
    }

    @Override
    public ItemPedidoDTO update(ItemPedidoDTO itemPedidoDTO, UUID itemPedidoId) {

        ItemPedidoModel itemPedidoModel = itemPedidoRepository.findById(itemPedidoId).orElseThrow(() -> new GlobalExeception("Nenhum item encontrado!"));

        itemPedidoModel.setQuantidadeItemConsumo(itemPedidoDTO.getQuantidadeItemConsumo());
        itemPedidoModel.setPrecoItemPedido(itemPedidoDTO.getPrecoItemPedido());
        itemPedidoModel.setDesconto(itemPedidoDTO.getDesconto());
        itemPedidoModel.setItemConsumo(itemPedidoDTO.getItemConsumo());
        itemPedidoModel.setDescricao(itemPedidoDTO.getDescricao());

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
    public ItemPedidoDTO init(ItemConsumoDTO itemConsumoDTO) {
        ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();

        itemPedidoDTO.setItemConsumo( itemConsumoMapper.itemConsumoDTOToItemConsumoModel(itemConsumoDTO) );
        itemPedidoDTO.setPrecoItemPedido(itemConsumoDTO.getPreco() );
        itemPedidoDTO.setDescricao(itemConsumoDTO.getDescricao() );

        itemPedidoDTO.setQuantidadeItemConsumo(itemPedidoDTO.getQuantidadeItemConsumo() == null ? 1L : itemPedidoDTO.getQuantidadeItemConsumo() );

        itemPedidoDTO.setDesconto(itemPedidoDTO.getDesconto() == null ? 0D : itemPedidoDTO.getDesconto());

        if (itemPedidoDTO.getDesconto() > 0){
            itemPedidoDTO.setPrecoItemPedido(itemPedidoDTO.getPrecoItemPedido() - itemPedidoDTO.getDesconto() );
        }

        itemPedidoDTO = itemPedidoMapper.itemPedidoModelToItemPedidoDTO( itemPedidoMapper.itemPedidoDTOToItemPedidoModel(itemPedidoDTO) );


        return itemPedidoDTO;
    }

    @Override
    public List<ItemPedidoDTO> itensPedidoByPedido(UUID pedidoId) {
        return itemPedidoRepository.findAll()
                .stream()
                .filter(x -> x.getPedido().getId().equals(pedidoId))
                .map(itemPedidoMapper::itemPedidoModelToItemPedidoDTO)
                .toList();
    }
}
