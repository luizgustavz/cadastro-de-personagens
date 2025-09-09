package com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.usecase;

import com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.domain.Generos;
import com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.repository.IGenerosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerosUsecase {

    private final IGenerosRepository repository;
    public GenerosUsecase(IGenerosRepository aRepository){
        this.repository = aRepository;
    }

    public Generos persist(Generos obj){
        return repository.save(obj);
    }

    public List<Generos> findAll(){
        return repository.findAll();
    }

    public Generos findById(Long id){
        return repository.findById(id).get();
    }

    public Generos update(Long id, Generos obj){
        if (repository.existsById(id)){
            obj.setId(id);
            return repository.save(obj);
        }
        return null;
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
