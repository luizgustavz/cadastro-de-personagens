package com.luizgustavz.cadastrodepersonagens.application.controller;

import com.luizgustavz.cadastrodepersonagens.application.dto.request.PersonRequest;
import com.luizgustavz.cadastrodepersonagens.application.dto.response.PersonResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Personagens")
@RequestMapping("/api/v1/person")
public interface PersonController {

    @PostMapping
    ResponseEntity<PersonResponse> createEntity(@Valid @RequestBody PersonRequest request);

    @GetMapping
    ResponseEntity<List<PersonResponse>> readEntity();

    @GetMapping("/{uuid}")
    ResponseEntity<PersonResponse> readEntity(@PathVariable UUID uuid);

    @GetMapping(params = "name")
    ResponseEntity<PersonResponse> readEntity(@RequestParam String name);

    @DeleteMapping(path = "/{uuid}")
    ResponseEntity<Void> deleteEntity(@PathVariable UUID uuid);

}
