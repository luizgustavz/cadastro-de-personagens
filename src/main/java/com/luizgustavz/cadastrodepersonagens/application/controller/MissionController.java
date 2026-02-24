package com.luizgustavz.cadastrodepersonagens.application.controller;

import com.luizgustavz.cadastrodepersonagens.application.dto.request.MissionRequest;
import com.luizgustavz.cadastrodepersonagens.application.dto.response.MissionResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Missões")
@RequestMapping("/api/v1/mission")
public interface MissionController {

    @PostMapping
    ResponseEntity<MissionResponse> addMission(@Valid @RequestBody MissionRequest request);

    @GetMapping("/{id}")
    ResponseEntity<MissionResponse> findByID(@PathVariable UUID id);

    @GetMapping
    ResponseEntity<List<MissionResponse>> findAll();

    @DeleteMapping("{id}")
    ResponseEntity<MissionResponse> deleteMission(@PathVariable UUID id);
}
