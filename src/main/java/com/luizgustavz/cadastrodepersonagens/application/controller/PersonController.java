package com.luizgustavz.cadastrodepersonagens.application.controller;

import com.luizgustavz.cadastrodepersonagens.application.dto.request.PersonRequest;
import com.luizgustavz.cadastrodepersonagens.application.dto.response.PersonResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Personagens")
@RequestMapping("/api/v1/person")
public interface PersonController {

    @PostMapping
    ResponseEntity<PersonResponse> createEntity(@Valid @RequestBody PersonRequest request);

    @GetMapping
    ResponseEntity<PersonResponse> readEntity();

    @GetMapping("/{id}")
    ResponseEntity<PersonResponse> readEntity(@PathVariable UUID uuid);

    @GetMapping(params = "/{name}")
    ResponseEntity<PersonResponse> readEntity(@RequestParam String name);

    @DeleteMapping(path = "/{uuid}")
    ResponseEntity<PersonResponse> deleteEntity(@PathVariable UUID uuid);

}
