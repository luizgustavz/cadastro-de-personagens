package com.luizgustavz.cadastrodepersonagens.domain.exceptions;

import com.luizgustavz.cadastrodepersonagens.domain.exceptions.messages.Error;

public sealed class DomainException extends RuntimeException permits
        FieldsNotNullOrBlankException, InvalidNameFormatException, InvalidAgeArgument, InvalidUriException {

    protected DomainException(Error e){
        super(e.value());
    }

    protected DomainException(Error e, String details){
        super(e.value() + ": " + details);
    }
}
