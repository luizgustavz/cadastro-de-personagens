package com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_personagens")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personagens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String obra;
    private String autor;

    @ManyToOne
    @JoinColumn(name = "id_generos_personagens_fk")
    private Generos generos;

}
