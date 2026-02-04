package com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions.message;

public enum Error {

    EXIST_BY_NAME("O nome informado já existe!!"),
    PERSON_NOT_FOUND("O personagem não existe ou nao foi cadastrado")
    ;

    private final String description;

    Error(final String description){
        this.description = description;
    }

    public String value(){
        return description;
    }

}
