package com.luizgustavz.cadastrodepersonagens.domain.exceptions;

import com.luizgustavz.cadastrodepersonagens.domain.exceptions.messages.Error;

public final class FieldsNotNullOrBlankException extends DomainException {

    public FieldsNotNullOrBlankException() {
        super(Error.FIELDS_NOT_NULL);
    }
}
