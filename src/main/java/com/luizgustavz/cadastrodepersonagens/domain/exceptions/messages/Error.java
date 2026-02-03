package com.luizgustavz.cadastrodepersonagens.domain.exceptions.messages;

public enum Error {

    FIELDS_NOT_NULL("Este campo é obrigatorio!!"),
    INVALID_NAME_PATTERN("O campo nome deve conter somente letras!!"),
    INVALID_AGE("A idade deve ser maior que 0 e menor que 100"),
    INVALID_URI("Uri de imagem invalida")

    ;

    private final String description;

    Error(final String description){
        this.description = description;
    }

    public String value(){
        return description;
    }
}
