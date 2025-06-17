package com.aluracursos.Challenge_LiterAlura;

import com.aluracursos.Challenge_LiterAlura.model.DatosLibro;
import com.aluracursos.Challenge_LiterAlura.principal.Principal;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiterAluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		DatosLibro datos = principal.obtenerDatosLibro();
		System.out.println(datos.autor());
		System.out.println(datos.titulo());
	}

}
