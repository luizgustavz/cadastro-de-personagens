package com.luizgustavz.cadastrodepersonagens.application.controller;

import com.luizgustavz.cadastrodepersonagens.application.dto.request.PersonRequest;
import com.luizgustavz.cadastrodepersonagens.application.dto.response.PersonResponse;
import com.luizgustavz.cadastrodepersonagens.application.mapper.PersonMapper;
import com.luizgustavz.cadastrodepersonagens.domain.entities.Person;
import com.luizgustavz.cadastrodepersonagens.infrastructure.services.PersonServicesImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonControllerImpl implements PersonController {

    private final PersonServicesImpl services;
    private final PersonMapper mapper;

    public PersonControllerImpl(
            PersonServicesImpl services,
            PersonMapper mapper
    ){
            this.services = services;
            this.mapper = mapper;
    }

    @Override
    public ResponseEntity<PersonResponse> createEntity(PersonRequest request) {

        Person entity = mapper.toEntity(request);
        Person saved = services.createEntity(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

}
