package com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions;

import com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions.message.Error;

public final class EntityNotFoundException extends ServiceViolationException {

    public EntityNotFoundException() {
        super(Error.PERSON_NOT_FOUND);
    }
}
