package com.luizgustavz.cadastrodepersonagens.application.dto.request;

import com.luizgustavz.cadastrodepersonagens.domain.enums.Rank;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public record PersonRequest(

        @NotNull(message = "O campo NAME nao pode ser nulo")
        @NotBlank(message = "O campo NAME nao deve estar em branco")
        String name,


        @NotNull(message = "O campo IMAGE nao pode ser nulo")
        @NotBlank(message = "O campo IMAGE nao deve estar em branco")
        String imageUrl,

        Rank rank,

        @Min(value = 1, message = "O valor minimo para idade é 1")
        @Max(value = 100, message = "O valor maximo para idade é 100")
        int age
) {
}
