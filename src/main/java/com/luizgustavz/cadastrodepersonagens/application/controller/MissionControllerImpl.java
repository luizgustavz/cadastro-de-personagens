package com.luizgustavz.cadastrodepersonagens.application.controller;

import com.luizgustavz.cadastrodepersonagens.application.dto.request.MissionRequest;
import com.luizgustavz.cadastrodepersonagens.application.dto.response.MissionResponse;
import com.luizgustavz.cadastrodepersonagens.infrastructure.services.MissionServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class MissionControllerImpl implements MissionController {

    private final MissionServices services;

    public MissionControllerImpl(
            MissionServices services
    ) {
            this.services = services;
    }

    @Override
    public ResponseEntity<MissionResponse> addMission(MissionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(services.createEntity(request));
    }

    @Override
    public ResponseEntity<MissionResponse> findByID(UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(services.findByID(id));
    }

    @Override
    public ResponseEntity<List<MissionResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(services.findAll());
    }

    @Override
    public ResponseEntity<MissionResponse> deleteMission(UUID id) {
        services.dropEntity(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
