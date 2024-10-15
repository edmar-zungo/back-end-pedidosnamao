package com.edmarzungo.pedidosnamao.controllers;


import com.edmarzungo.pedidosnamao.enumerations.EstadoPedido;
import com.edmarzungo.pedidosnamao.services.PedidoService;
import com.edmarzungo.pedidosnamao.services.dtos.PedidoDTO;
import com.edmarzungo.pedidosnamao.services.dtos.PedidoPageDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("")
    public ResponseEntity<PedidoPageDTO> getAll(@RequestParam int pageNumber, @RequestParam(defaultValue = "5") int pageItens){
        PedidoPageDTO pedidoPageDTO = pedidoService.getAllPageble(pageNumber, pageItens);
        return new ResponseEntity<>(pedidoPageDTO, HttpStatus.OK);
    }
//    @GetMapping("")
//    public ResponseEntity<List<PedidoDTO>> getAll(){
//        List<PedidoDTO> pedidoDTOList = pedidoService.getAll();
//        return new ResponseEntity<>(pedidoDTOList, HttpStatus.OK);
//    }

    @PostMapping("")
    public ResponseEntity<PedidoDTO> save(){
        PedidoDTO pedidoSaved = pedidoService.init();
        return new ResponseEntity<>(pedidoSaved, HttpStatus.CREATED);
    }

    @PutMapping("/mudar-estado")
    public ResponseEntity<PedidoDTO> mudarEstadoPedido(@Valid @RequestBody PedidoDTO pedidoDTO){
        PedidoDTO pedidoUpdated = pedidoService.mudarEstadoPedido(pedidoDTO);
        return new ResponseEntity<>(pedidoUpdated, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> update(@PathVariable(value = "id") UUID id, @Valid @RequestBody PedidoDTO pedidoDTO){
        PedidoDTO pedidoUpdated = pedidoService.update(pedidoDTO, id);
        return new ResponseEntity<>(pedidoUpdated, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getOne(@PathVariable(value = "id") UUID id){
        PedidoDTO pedidoResult = pedidoService.getOne(id);
        return new ResponseEntity<>(pedidoResult, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") UUID id){
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/calculaTotalPagar/{id}")
    public ResponseEntity<PedidoDTO> calculaTotalPagar(@PathVariable(value = "id") UUID id){
        PedidoDTO pedidoResult = pedidoService.calculaTotalPagar(id);
        return new ResponseEntity<>(pedidoResult, HttpStatus.OK);
    }
}
