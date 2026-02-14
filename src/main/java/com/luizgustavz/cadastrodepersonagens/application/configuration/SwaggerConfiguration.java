package com.luizgustavz.cadastrodepersonagens.application.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI swagger(){
        return new OpenAPI()
                .info(new Info().title("Cadastro de Personagens").description("REST API para cadastro de personagens").version("1.0.1"));
    }
}
