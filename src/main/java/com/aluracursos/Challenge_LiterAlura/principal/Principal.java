package com.aluracursos.Challenge_LiterAlura.principal;

import com.aluracursos.Challenge_LiterAlura.model.DatosLibro;
import com.aluracursos.Challenge_LiterAlura.service.ConsumoAPI;
import com.google.gson.Gson;

import java.util.Scanner;

public class Principal {
    //ATRIBUTES
    private final String baseApiURL = "https://gutendex.com/books/";
    private final String queryApiAux = "?search=";
    private final String spaceApiAux = "%20";
    ConsumoAPI consumoAPI = new ConsumoAPI();
    private Scanner  teclado = new Scanner(System.in);

    //METHODS
    public DatosLibro obtenerDatosLibro(){
        System.out.println("Ingresa el nombre del libro...");
        String tituloLibro = teclado.nextLine();

        System.out.println("Obteniendo datos de... " + baseApiURL+queryApiAux+tituloLibro+spaceApiAux);
        String datos = consumoAPI.obtenerDatos(baseApiURL+queryApiAux+tituloLibro+spaceApiAux);

        System.out.println("imprimiendo datos...\n");
        System.out.println(datos);
        System.out.println("\nFinish");
        return new Gson().fromJson(datos, DatosLibro.class);
    }
}
