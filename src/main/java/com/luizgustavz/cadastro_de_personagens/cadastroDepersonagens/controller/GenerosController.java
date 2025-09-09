package com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.controller;

import com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.domain.Generos;
import com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.usecase.GenerosUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/generos")
public class GenerosController {

    private final GenerosUsecase service;
    public GenerosController(GenerosUsecase aService){
        this.service = aService;
    }


    @PostMapping(value = "/save")
    public ResponseEntity<Generos> persist(@RequestBody Generos obj){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.persist(obj));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Generos>> findAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findAll());
    }

    @GetMapping(value = "/list/id")
    public ResponseEntity<Generos> findById(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findById(id));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Generos> update(@PathVariable Long id, @RequestBody Generos obj){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(id, obj));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
