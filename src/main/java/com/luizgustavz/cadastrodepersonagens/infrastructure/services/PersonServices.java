package com.luizgustavz.cadastrodepersonagens.infrastructure.services;

import com.luizgustavz.cadastrodepersonagens.application.dto.request.PersonRequest;
import com.luizgustavz.cadastrodepersonagens.application.dto.response.PersonResponse;
import com.luizgustavz.cadastrodepersonagens.application.mapper.PersonMapper;
import com.luizgustavz.cadastrodepersonagens.domain.entities.Person;
import com.luizgustavz.cadastrodepersonagens.domain.repositories.PersonRepository;
import com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions.DataViolationNameException;
import com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonServices {

    private final PersonRepository repository;
    private final PersonMapper mapper;

    public PersonServices(
            PersonRepository repository,
            PersonMapper mapper
    ) {
            this.repository = repository;
            this.mapper = mapper;
    }

    public PersonResponse createEntity(PersonRequest request) {
        if (repository.existsByName(request.name().toLowerCase())) throw new DataViolationNameException();
        Person saved = repository.save(mapper.toEntity(request));
        return mapper.toDto(saved);
    }

    public PersonResponse findById(UUID uuid) {
        Person entity = repository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        return mapper.toDto(entity);
    }

    public List<PersonResponse> findAllEntities() {
        List<Person> entities = repository.findAll();
        return entities.stream().map(mapper::toDto).toList();
    }

    public PersonResponse findByName(String n) {
        Person person = repository.findByName(n).orElseThrow(EntityNotFoundException::new);
        return mapper.toDto(person);
    }

    public void dropEntity(UUID uuid) {
        if (!repository.existsById(uuid)) throw new EntityNotFoundException();
        repository.deleteById(uuid);
    }
}
