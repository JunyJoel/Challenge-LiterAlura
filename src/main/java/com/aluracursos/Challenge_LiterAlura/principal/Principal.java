package com.aluracursos.Challenge_LiterAlura.principal;

import com.aluracursos.Challenge_LiterAlura.dto.DataApiResponse;
import com.aluracursos.Challenge_LiterAlura.service.ConsumoAPI;
import com.aluracursos.Challenge_LiterAlura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    //ATRIBUTES
    private final String baseApiURL = "https://gutendex.com/books/";
    private final String queryApiAux = "?search=";
    private final String spaceApiAux = "%20";
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvierteDatos conversor = new ConvierteDatos();
    private Scanner  teclado = new Scanner(System.in);

    //METHODS
    public DataApiResponse obtenerDatosLibro() {
        System.out.println("Ingresa el nombre del libro...");
        String tituloLibro = teclado.nextLine();

        System.out.println("Obteniendo datos de... " + baseApiURL + queryApiAux + tituloLibro + spaceApiAux);
        String json = consumoAPI.obtenerDatos(baseApiURL + queryApiAux + tituloLibro + spaceApiAux);
        System.out.println(json);

        DataApiResponse resultado = conversor.obtenerDatos(json, DataApiResponse.class);

        return resultado;
    }
}
