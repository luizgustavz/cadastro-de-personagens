package com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions;

import com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions.message.Error;

public final class DataViolationNameException extends ServiceViolationException {

    public DataViolationNameException() {
        super(Error.EXIST_BY_NAME);
    }
}
