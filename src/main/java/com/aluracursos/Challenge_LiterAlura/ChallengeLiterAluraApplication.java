package com.aluracursos.Challenge_LiterAlura;

import com.aluracursos.Challenge_LiterAlura.dto.DataApiResponse;
import com.aluracursos.Challenge_LiterAlura.dto.DatosAutor;
import com.aluracursos.Challenge_LiterAlura.dto.DatosLibro;
import com.aluracursos.Challenge_LiterAlura.principal.Principal;
import com.aluracursos.Challenge_LiterAlura.repository.AutorRepository;
import com.aluracursos.Challenge_LiterAlura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ChallengeLiterAluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiterAluraApplication.class, args);
	}
	@Autowired
	private AutorRepository autorRepositorio;
	@Autowired
	private LibroRepository libroRepositorio;

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(autorRepositorio, libroRepositorio);
		principal.muestraMenu();
	}
}
