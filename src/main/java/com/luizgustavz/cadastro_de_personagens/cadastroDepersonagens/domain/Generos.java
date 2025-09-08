package com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_generos")
public class Generos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private CategoriaEnumerada categoria;

    @OneToMany(mappedBy = "generos")
    private List<Personagens> personagens;

    public Generos(){

    }

    public Generos(String id, CategoriaEnumerada categoria, List<Personagens> personagens) {
        this.id = id;
        this.categoria = categoria;
        this.personagens = personagens;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CategoriaEnumerada getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEnumerada categoria) {
        this.categoria = categoria;
    }

    public List<Personagens> getPersonagens() {
        return personagens;
    }

    public void setPersonagens(List<Personagens> personagens) {
        this.personagens = personagens;
    }
}
