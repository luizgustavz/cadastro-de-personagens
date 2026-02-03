package com.luizgustavz.cadastrodepersonagens.domain.exceptions;

import com.luizgustavz.cadastrodepersonagens.domain.exceptions.messages.Error;

public final class InvalidNameFormatException extends DomainException {

    public InvalidNameFormatException() {
        super(Error.INVALID_NAME_PATTERN, "Deve conter no mínimo 3 caracteres e no máximo 60");
    }
}
