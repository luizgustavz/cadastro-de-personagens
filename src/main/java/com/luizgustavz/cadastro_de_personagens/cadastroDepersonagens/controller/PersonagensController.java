package com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.controller;

import com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.domain.Personagens;
import com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.usecase.PersonagensUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "api/v1/personagens")
@RestController
public class PersonagensController {

    private final PersonagensUsecase service;
    public PersonagensController(PersonagensUsecase aService){
        this.service = aService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Personagens> persist(@RequestBody Personagens obj){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.persist(obj));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Personagens>> findAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findAll());
    }

    @GetMapping(value = "/list/id")
    public ResponseEntity<Personagens> findById(@RequestParam Long id){
       return ResponseEntity.status(HttpStatus.OK)
               .body(service.findById(id));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Personagens> update(@PathVariable Long id, @RequestBody Personagens obj){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(id, obj));
    }

    @DeleteMapping(value = "/drop/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();

    }


}
