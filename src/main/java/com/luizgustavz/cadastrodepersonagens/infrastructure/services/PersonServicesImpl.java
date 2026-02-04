package com.luizgustavz.cadastrodepersonagens.infrastructure.services;

import com.luizgustavz.cadastrodepersonagens.domain.entities.Person;
import com.luizgustavz.cadastrodepersonagens.domain.repositories.PersonRepository;
import com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions.DataViolationNameException;
import com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonServicesImpl implements CRUDServices<Person, UUID> {

    private final PersonRepository repository;

    public PersonServicesImpl(
            PersonRepository repository
    ) {
            this.repository = repository;
    }

    @Override
    @Transactional
    public Person createEntity(Person entity) {

        if (repository.existsByName(entity.getName())) {
            throw new DataViolationNameException();
        }

        return repository.save(entity);
    }

    @Override
    public Person findById(UUID uuid) {
        return repository.findById(uuid).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Person> findAllEntities() {
        return repository.findAll();
    }

    @Override
    public Person findByName(String n) {
        final String name = normalize(n);
        return repository.findByName(name).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public void dropEntity(UUID uuid) {
        if (!repository.existsById(uuid)){
            throw new EntityNotFoundException();
        }
        repository.deleteById(uuid);
    }

    private static String normalize(final String v){
        return v.toLowerCase();
    }
}
