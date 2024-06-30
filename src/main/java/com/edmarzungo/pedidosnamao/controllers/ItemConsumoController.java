package com.edmarzungo.pedidosnamao.controllers;

import com.edmarzungo.pedidosnamao.services.ItemConsumoService;
import com.edmarzungo.pedidosnamao.services.dtos.ItemConsumoDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/itens-consumo")
public class ItemConsumoController {
    private final ItemConsumoService itemConsumoService;

    public ItemConsumoController(ItemConsumoService itemConsumoService) {
        this.itemConsumoService = itemConsumoService;
    }

    @GetMapping("")
    public ResponseEntity<List<ItemConsumoDTO>> getAll(){
        List<ItemConsumoDTO> itemConsumoDTOList = itemConsumoService.getAll();
        return ResponseEntity.ok().body(itemConsumoDTOList);
    }

    @PostMapping("")
    public ResponseEntity<ItemConsumoDTO> saveMesa(@Valid @RequestBody ItemConsumoDTO itemConsumoDTO){
        ItemConsumoDTO itemConsumoSaved = itemConsumoService.save(itemConsumoDTO);
        return new ResponseEntity<>(itemConsumoSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemConsumoDTO> getOne(@PathVariable(value = "id") UUID id, @Valid @RequestBody ItemConsumoDTO itemConsumoDTO){
        ItemConsumoDTO itemConsumoUpdated = itemConsumoService.update(itemConsumoDTO, id);
        return new ResponseEntity<>(itemConsumoUpdated, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ItemConsumoDTO> getOne(@PathVariable(value = "id") UUID id){
        ItemConsumoDTO itemConsumoResult = itemConsumoService.getOne(id);
        return new ResponseEntity<>(itemConsumoResult, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") UUID id){
        itemConsumoService.delete(id);
        return new ResponseEntity<>("Eliminado com sucesso!", HttpStatus.OK);
    }
}
