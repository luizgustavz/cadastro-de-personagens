package com.luizgustavz.cadastrodepersonagens.application.dto.request;

import com.luizgustavz.cadastrodepersonagens.domain.enums.Difficulty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MissionRequest(

        @NotBlank(message = "O campo nome é obrigatorio")
        @NotNull(message = "O campo nome nao deve ser nulo")
        String name,


        Difficulty difficulty
) {
}
