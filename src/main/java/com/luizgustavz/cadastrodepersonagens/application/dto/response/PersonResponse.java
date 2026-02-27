package com.luizgustavz.cadastrodepersonagens.application.dto.response;

import com.luizgustavz.cadastrodepersonagens.domain.enums.Rank;

import java.util.UUID;

public record PersonResponse(
        UUID uuid,
        String name,
        String imageUrl,
        Rank rank,
        int age
) {
}
