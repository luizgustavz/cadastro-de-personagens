package com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions;

import com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions.message.Error;

public sealed class ServiceViolationException extends RuntimeException permits
DataViolationNameException, EntityNotFoundException {

    protected ServiceViolationException(Error e){
        super(e.value());
    }

    protected ServiceViolationException(Error e, String details){
        super(e.value() + ": " + details);
    }
}
