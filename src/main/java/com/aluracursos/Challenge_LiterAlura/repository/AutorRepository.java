package com.aluracursos.Challenge_LiterAlura.repository;

import com.aluracursos.Challenge_LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Autor findByNombre(String nombre);
    @Query("SELECT a.nombre FROM Autor a")
    List<String> obtenerAutores();
    @Query("SELECT a.nombre FROM Autor a WHERE a.nacimiento < :fechaBuscada AND a.defuncion > :fechaBuscada")
    List<String> obtenerAutoresVivosPorFecha(String fechaBuscada);
}
