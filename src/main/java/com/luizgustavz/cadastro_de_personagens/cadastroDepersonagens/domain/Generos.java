package com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_generos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Generos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private CategoriaEnumerada categoria;

    @OneToMany(mappedBy = "generos")
    private List<Personagens> personagens;
}
