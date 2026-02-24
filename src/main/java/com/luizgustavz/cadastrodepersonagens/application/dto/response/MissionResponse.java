package com.luizgustavz.cadastrodepersonagens.application.dto.response;

import com.luizgustavz.cadastrodepersonagens.domain.enums.Difficulty;

import java.util.UUID;

public record MissionResponse(
        UUID uuid,
        String name,
        Difficulty difficulty
) {
}
