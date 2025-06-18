package com.aluracursos.Challenge_LiterAlura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonProperty("title") String titulo,
        @JsonProperty("authors")List<DatosAutor> autores,
        @JsonProperty("languages")List<String> idiomas){
}
