package com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.repository;

import com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.domain.Generos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenerosRepository extends JpaRepository<Generos, Long> {
}
