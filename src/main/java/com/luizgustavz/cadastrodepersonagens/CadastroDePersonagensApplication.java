package com.luizgustavz.cadastrodepersonagens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("test")
public class CadastroDePersonagensApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroDePersonagensApplication.class, args);
	}

}
