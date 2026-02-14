package com.luizgustavz.cadastrodepersonagens.domain.repositories;

import com.luizgustavz.cadastrodepersonagens.domain.entities.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MissionRepository extends JpaRepository<Mission, UUID> {

    Optional<Mission> findByName(String name);
}
