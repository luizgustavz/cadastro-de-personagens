package com.luizgustavz.cadastrodepersonagens.infrastructure.services;

import com.luizgustavz.cadastrodepersonagens.application.dto.request.MissionRequest;
import com.luizgustavz.cadastrodepersonagens.application.dto.response.MissionResponse;
import com.luizgustavz.cadastrodepersonagens.application.mapper.MissionMapper;
import com.luizgustavz.cadastrodepersonagens.domain.entities.Mission;
import com.luizgustavz.cadastrodepersonagens.domain.repositories.MissionRepository;
import com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions.EntityNotFoundException;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MissionServices {

    private final MissionRepository repository;
    private final MissionMapper mapper;

    public MissionServices(
            MissionRepository repository,
            MissionMapper mapper
    ) {
            this.repository = repository;
            this.mapper = mapper;
    }

    public MissionResponse createEntity(MissionRequest request){
        Mission mission = repository.save(mapper.toEntity(request));
        return mapper.toDTO(mission);
    }

    public MissionResponse findByID(UUID uuid){
        Mission mission = repository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        return mapper.toDTO(mission);
    }

    public List<MissionResponse> findAll(){
        List<Mission> missions = repository.findAll();
        return missions.stream().map(mapper::toDTO).toList();
    }

    public void dropEntity(UUID uuid){
        if (!repository.existsById(uuid)) throw new EntityNotFoundException();
        repository.deleteById(uuid);
    }



}
