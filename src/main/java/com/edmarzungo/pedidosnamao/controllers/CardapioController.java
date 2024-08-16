package com.edmarzungo.pedidosnamao.controllers;

import com.edmarzungo.pedidosnamao.services.CardapioService;
import com.edmarzungo.pedidosnamao.services.dtos.CardapioDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cardapios")
public class CardapioController {
    private final CardapioService cardapioService;

    public CardapioController(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }

    @GetMapping("")
    public ResponseEntity<List<CardapioDTO>> getAll(){
        List<CardapioDTO> cardapioDTOList = cardapioService.getAll();
        return ResponseEntity.ok().body(cardapioDTOList);
    }

    @PostMapping("")
    public ResponseEntity<CardapioDTO> save(@Valid @RequestBody CardapioDTO cardapioDTO){
        CardapioDTO cardapioSaved = cardapioService.save(cardapioDTO);
        return new ResponseEntity<>(cardapioSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardapioDTO> update(@PathVariable(value = "id") UUID id, @Valid @RequestBody CardapioDTO cardapioDTO){
        CardapioDTO cardapioUpdated = cardapioService.update(cardapioDTO, id);
        return new ResponseEntity<>(cardapioUpdated, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CardapioDTO> getOne(@PathVariable(value = "id") UUID id){
        CardapioDTO cardapioResult = cardapioService.getOne(id);
        return new ResponseEntity<>(cardapioResult, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") UUID id){
        cardapioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
