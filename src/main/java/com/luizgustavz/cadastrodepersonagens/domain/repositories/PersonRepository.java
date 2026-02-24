package com.luizgustavz.cadastrodepersonagens.domain.repositories;

import com.luizgustavz.cadastrodepersonagens.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

    Optional<Person> findByName(final String n);

    boolean existsByName(final String n);

}
