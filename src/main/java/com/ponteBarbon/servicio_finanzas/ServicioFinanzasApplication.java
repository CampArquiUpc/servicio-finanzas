package com.ponteBarbon.servicio_finanzas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ServicioFinanzasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioFinanzasApplication.class, args);
	}

}
