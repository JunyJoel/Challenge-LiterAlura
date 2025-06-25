package com.aluracursos.Challenge_LiterAlura.repository;

import com.aluracursos.Challenge_LiterAlura.model.Autor;
import com.aluracursos.Challenge_LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    Libro findByTituloContainingIgnoreCase(String titulo);
    //List<Libro> findALlOrderById();
    @Query("SELECT l.titulo FROM Libro l")
    List<String> obtenerLibros();
}
