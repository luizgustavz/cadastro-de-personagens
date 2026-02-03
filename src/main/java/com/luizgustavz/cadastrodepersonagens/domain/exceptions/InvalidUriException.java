package com.luizgustavz.cadastrodepersonagens.domain.exceptions;

import com.luizgustavz.cadastrodepersonagens.domain.exceptions.messages.Error;

public final class InvalidUriException extends DomainException {

    public InvalidUriException() {
        super(Error.INVALID_URI);
    }
}
