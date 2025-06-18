package com.aluracursos.Challenge_LiterAlura;

import com.aluracursos.Challenge_LiterAlura.dto.DataApiResponse;
import com.aluracursos.Challenge_LiterAlura.principal.Principal;
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
		DataApiResponse datos = principal.obtenerDatosLibro();
		datos.libros().forEach(d -> {
			System.out.println(d.titulo());
			d.autores().forEach(a -> System.out.println(a.nombre()));
		});
	}

}
