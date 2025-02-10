package com.edmarzungo.pedidosnamao.controllers;

import com.edmarzungo.pedidosnamao.services.ItemPedidoService;
import com.edmarzungo.pedidosnamao.services.dtos.ItemConsumoDTO;
import com.edmarzungo.pedidosnamao.services.dtos.ItemPedidoDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/itens-pedido")
public class ItemPedidoController {
    private final ItemPedidoService itemPedidoService;

    public ItemPedidoController(ItemPedidoService itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    @GetMapping("")
    public ResponseEntity<List<ItemPedidoDTO>> getAll(){
        List<ItemPedidoDTO> itemPedidoDTOList = itemPedidoService.getAll();
        return ResponseEntity.ok().body(itemPedidoDTOList);
    }

    @GetMapping("/by-pedido/{pedidoId}")
    public ResponseEntity<List<ItemPedidoDTO>> getAllByPedido(@PathVariable(value = "pedidoId") UUID pedidoId){
        List<ItemPedidoDTO> itemPedidoDTOList = itemPedidoService.itensPedidoByPedido(pedidoId);
        return ResponseEntity.ok().body(itemPedidoDTOList);
    }

    @PostMapping("")
    public ResponseEntity<ItemPedidoDTO> save(@Valid @RequestBody ItemPedidoDTO itemPedidoDTO){
        ItemPedidoDTO itemPedidoSaved = itemPedidoService.save(itemPedidoDTO);
        return new ResponseEntity<>(itemPedidoSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoDTO> getOne(@PathVariable(value = "id") UUID id, @Valid @RequestBody ItemPedidoDTO itemPedidoDTO){
        ItemPedidoDTO itemPedidoUpdated = itemPedidoService.update(itemPedidoDTO, id);
        return new ResponseEntity<>(itemPedidoUpdated, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoDTO> getOne(@PathVariable(value = "id") UUID id){
        ItemPedidoDTO itemPedidoResult = itemPedidoService.getOne(id);
        return new ResponseEntity<>(itemPedidoResult, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") UUID id){
        itemPedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/adicionar-item")
    public ResponseEntity<ItemPedidoDTO> adicionarItemPedido(@RequestBody ItemConsumoDTO itemConsumoDTO){
        return ResponseEntity.ok( itemPedidoService.init(itemConsumoDTO) );
    }
}
