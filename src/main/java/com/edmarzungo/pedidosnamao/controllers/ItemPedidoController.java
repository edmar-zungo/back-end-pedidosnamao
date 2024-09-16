package com.edmarzungo.pedidosnamao.controllers;

import com.edmarzungo.pedidosnamao.services.ItemPedidoService;
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
    public ResponseEntity<String> delete(@PathVariable(value = "id") UUID id){
        itemPedidoService.delete(id);
        return new ResponseEntity<>("Eliminado com sucesso!", HttpStatus.OK);
    }
}
