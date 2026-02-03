package com.luizgustavz.cadastrodepersonagens.domain.validate;

import java.util.regex.Pattern;

public final class Patterns {

    private static final Pattern NAME_PATTERN  = Pattern.compile("^[A-Za-zÀ-ÿ ]+$");

    private Patterns(){};

    public static boolean isValid(String v){
       return NAME_PATTERN.matcher(v).matches();
    }

}
