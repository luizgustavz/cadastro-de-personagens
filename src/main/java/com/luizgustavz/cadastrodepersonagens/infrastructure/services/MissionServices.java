package com.luizgustavz.cadastrodepersonagens.infrastructure.services;

import com.luizgustavz.cadastrodepersonagens.domain.entities.Mission;
import com.luizgustavz.cadastrodepersonagens.domain.repositories.MissionRepository;
import com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MissionServicesImpl {

    private final MissionRepository repository;

    public MissionServicesImpl(
            MissionRepository repository
    ) {
            this.repository = repository;
    }


}
