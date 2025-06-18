package com.aluracursos.Challenge_LiterAlura.model;

import com.aluracursos.Challenge_LiterAlura.dto.DatosAutor;
import com.aluracursos.Challenge_LiterAlura.dto.DatosLibro;

import java.util.List;

//@Entity
//@Table (name = "libros")
public class Libro {
    //@Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String titulo;
    private List<DatosAutor> autores;
    private List<String> idiomas;

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.autores = datosLibro.autores();
        this.idiomas = datosLibro.idiomas();
    }

    @Override
public String toString(){
    return  "titulo=" + titulo +
            "autor=" + autores +
            "idioma= " + idiomas;

}
}
