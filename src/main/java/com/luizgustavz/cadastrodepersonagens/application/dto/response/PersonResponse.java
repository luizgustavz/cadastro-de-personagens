package com.luizgustavz.cadastrodepersonagens.application.dto.response;

import com.luizgustavz.cadastrodepersonagens.domain.enums.Rank;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public record PersonResponse(
        UUID id,
        String name,
        String imageUrl,
        Rank rank,
        int age
) {
}
