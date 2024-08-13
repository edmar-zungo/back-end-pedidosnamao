package com.edmarzungo.pedidosnamao.controllers;

import com.edmarzungo.pedidosnamao.services.MesaService;
import com.edmarzungo.pedidosnamao.services.dtos.MesaDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mesas")
public class MesaController {

    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping("")
    public ResponseEntity<List<MesaDTO>> getAll(){
        List<MesaDTO> mesaDTOList = mesaService.getAll();
        return ResponseEntity.ok().body(mesaDTOList);
    }

    @PostMapping("")
    public ResponseEntity<MesaDTO> saveMesa(@Valid @RequestBody MesaDTO mesaDTO){
        MesaDTO mesaSaved = mesaService.save(mesaDTO);
        return new ResponseEntity<MesaDTO>(mesaSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MesaDTO> getOne(@PathVariable(value = "id") UUID id, @Valid @RequestBody MesaDTO mesaDTO){
        MesaDTO mesaUpdated = mesaService.update(mesaDTO, id);
        return new ResponseEntity<>(mesaUpdated, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MesaDTO> getOne(@PathVariable(value = "id") UUID id){
        MesaDTO mesaResult = mesaService.getOne(id);
        return new ResponseEntity<>(mesaResult, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") UUID id){
        mesaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
