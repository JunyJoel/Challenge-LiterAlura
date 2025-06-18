package com.aluracursos.Challenge_LiterAlura.model;

import com.aluracursos.Challenge_LiterAlura.dto.DatosAutor;

import java.util.List;

//@Entity
//@Table (name = "autores")
public class Autor {
    //@Id
    //@GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    private String nacimiento;
    private String defuncion;
    private List<String> libros;

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.nacimiento = datosAutor.fechaNacimiento();
        this.defuncion = datosAutor.fechaDefuncion();
    }
}
