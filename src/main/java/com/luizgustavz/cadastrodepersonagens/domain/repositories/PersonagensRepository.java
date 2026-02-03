package com.luizgustavz.cadastrodepersonagens.domain.repositories;

import com.luizgustavz.cadastrodepersonagens.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonagensRepository extends JpaRepository<Person, UUID> {

}
