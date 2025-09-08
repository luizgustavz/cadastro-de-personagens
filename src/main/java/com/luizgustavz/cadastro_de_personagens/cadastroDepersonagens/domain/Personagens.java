package com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_personagens")
public class Personagens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String obra;
    private String autor;

    public Personagens() {
    }

    public Personagens(Long id, String nome, String obra, String autor) {
        this.id = id;
        this.nome = nome;
        this.obra = obra;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
