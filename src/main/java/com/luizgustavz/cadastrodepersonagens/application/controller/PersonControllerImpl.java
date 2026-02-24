package com.luizgustavz.cadastrodepersonagens.application.controller;

import com.luizgustavz.cadastrodepersonagens.application.dto.request.PersonRequest;
import com.luizgustavz.cadastrodepersonagens.application.dto.response.PersonResponse;
import com.luizgustavz.cadastrodepersonagens.infrastructure.services.PersonServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class PersonControllerImpl implements PersonController {

    private final PersonServices services;

    public PersonControllerImpl(
            PersonServices services
    ){
            this.services = services;
    }

    @Override
    public ResponseEntity<PersonResponse> createEntity(PersonRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(services.createEntity(request));
    }

    @Override
    public ResponseEntity<List<PersonResponse>> readEntity() {
        return ResponseEntity.status(HttpStatus.OK).body(services.findAllEntities());
    }

    @Override
    public ResponseEntity<PersonResponse> readEntity(UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(services.findById(uuid));
    }

    @Override
    public ResponseEntity<PersonResponse> readEntity(String name) {
        return ResponseEntity.status(HttpStatus.OK).body(services.findByName(name));
    }

    @Override
    public ResponseEntity<Void> deleteEntity(UUID uuid) {
        services.dropEntity(uuid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
