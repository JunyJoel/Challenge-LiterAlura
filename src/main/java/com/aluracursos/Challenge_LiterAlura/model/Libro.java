package com.aluracursos.Challenge_LiterAlura.model;

import com.aluracursos.Challenge_LiterAlura.dto.DatosAutor;
import com.aluracursos.Challenge_LiterAlura.dto.DatosLibro;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne()
    //@JoinColumn(name = "autor_id")
    private Autor autor;

    private String idioma;
    private Double numeroDeDescargas;

    public Libro(){}

    public Libro(DatosLibro datos, Autor autor) {
        this.titulo = datos.titulo();
        this.autor = autor;
        this.idioma = datos.idiomas().get(0);
        this.numeroDeDescargas = datos.numeroDeDescargas();
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

    public Autor getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }
    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
public String toString(){
    return  "titulo=" + titulo +
            "autor=" + autor +
            "idioma= " + idioma;

}
}
