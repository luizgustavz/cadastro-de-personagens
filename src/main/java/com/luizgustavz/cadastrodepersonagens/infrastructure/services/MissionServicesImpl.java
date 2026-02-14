package com.luizgustavz.cadastrodepersonagens.infrastructure.services;

import com.luizgustavz.cadastrodepersonagens.domain.entities.Mission;
import com.luizgustavz.cadastrodepersonagens.domain.repositories.MissionRepository;
import com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MissionServicesImpl implements CRUDServices<Mission, UUID> {

    private final MissionRepository repository;

    public MissionServicesImpl(
            MissionRepository repository
    ) {
            this.repository = repository;
    }

    @Override
    public Mission createEntity(Mission entity) {

        return repository.save(entity);
    }

    @Override
    public Mission findById(UUID uuid) {

        return repository.findById(uuid).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Mission> findAllEntities() {

        return repository.findAll();
    }

    @Override
    public Mission findByName(String n) {

        return repository.findByName(n.toLowerCase()).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void dropEntity(UUID uuid) {

        if (!repository.existsById(uuid)){
            throw new EntityNotFoundException();
        }
        repository.deleteById(uuid);
    }
}
