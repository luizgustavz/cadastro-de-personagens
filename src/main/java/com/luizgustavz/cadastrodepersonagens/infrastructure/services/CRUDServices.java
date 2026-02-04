package com.luizgustavz.cadastrodepersonagens.infrastructure.services;

import java.util.List;

public interface CRUDServices<T, ID> {

    T createEntity(T entity);
    T findById(ID id);
    List<T> findAllEntities();
    T findByName(String n);
    void dropEntity(ID id);

}
