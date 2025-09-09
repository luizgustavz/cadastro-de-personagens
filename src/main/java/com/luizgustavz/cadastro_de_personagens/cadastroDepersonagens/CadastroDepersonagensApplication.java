package com.luizgustavz.cadastro_de_personagens.cadastroDepersonagens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("test")
public class CadastroDepersonagensApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroDepersonagensApplication.class, args);
	}

}
