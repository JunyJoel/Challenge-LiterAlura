package com.aluracursos.Challenge_LiterAlura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DataApiResponse(@JsonAlias("results")List<DatosLibro> libros) {
}
