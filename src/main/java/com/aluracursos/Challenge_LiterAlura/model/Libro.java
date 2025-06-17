package com.aluracursos.Challenge_LiterAlura.model;

import jakarta.persistence.*;

@Entity
@Table (name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String titulo;
    private String autor;
    private String idioma;
    private String sumario;

public Libro(DatosLibro datosLibro){
    this.titulo = datosLibro.titulo();
    this.autor = datosLibro.autor();
    this.idioma = datosLibro.idioma();
    this.sumario = datosLibro.sumario();
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getSumario() {
        return sumario;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

    @Override
public String toString(){
    return  "titulo=" + titulo +
            "autor=" + autor +
            "idioma= " + idioma +
            "sumario=" + sumario;
}
}
