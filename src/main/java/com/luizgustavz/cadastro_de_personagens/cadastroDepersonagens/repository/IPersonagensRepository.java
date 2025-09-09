package com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.repository;

import com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.domain.Personagens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonagensRepository extends JpaRepository<Personagens, Long> {
}
