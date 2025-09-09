package com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.usecase;

import com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.domain.Personagens;
import com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens.repository.IPersonagensRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonagensUsecase {

    private final IPersonagensRepository repository;
    public PersonagensUsecase(IPersonagensRepository aRepository){
        this.repository = aRepository;
    }

    public Personagens persist(Personagens obj){
        return repository.save(obj);
    }

    public List<Personagens> findAll(){
        return repository.findAll();
    }

    public Personagens findById(Long id){
        return repository.findById(id).get();
    }

    public Personagens update(Long id, Personagens obj){
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
