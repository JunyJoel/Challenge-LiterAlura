package com.aluracursos.Challenge_LiterAlura.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table (name = "autores")
public class Autor {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    private Double nacimiento;
    private Double defuncion;
    private String paisDeNacimiento;
    private List<String> libros;
}
