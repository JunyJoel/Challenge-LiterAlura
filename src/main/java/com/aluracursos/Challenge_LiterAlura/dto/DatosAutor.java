package com.aluracursos.Challenge_LiterAlura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonProperty("name") String nombre,
        @JsonProperty("birth_year") String fechaNacimiento,
        @JsonProperty("death_year") String fechaDefuncion){
}
