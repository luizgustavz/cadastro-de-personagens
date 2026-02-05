package com.luizgustavz.cadastrodepersonagens.domain.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Rank {

    A,
    B,
    C,
    D,
    E,

    @JsonEnumDefaultValue
    UNKNOWN

}
