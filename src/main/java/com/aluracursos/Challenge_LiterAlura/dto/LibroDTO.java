package com.aluracursos.Challenge_LiterAlura.dto;

import java.util.List;

public record LibroDTO(String titulo,
                       List<String> autor,
                       List<String> idioma) {
}
