package com.luizgustavz.cadastrodepersonagens.domain.exceptions;

import com.luizgustavz.cadastrodepersonagens.domain.exceptions.messages.Error;

public final class InvalidAgeArgument extends DomainException {

    public InvalidAgeArgument() {
        super(Error.INVALID_AGE);
    }
}
