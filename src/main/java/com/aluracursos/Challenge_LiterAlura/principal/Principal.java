package com.aluracursos.Challenge_LiterAlura.principal;

import com.aluracursos.Challenge_LiterAlura.dto.DataApiResponse;
import com.aluracursos.Challenge_LiterAlura.dto.DatosAutor;
import com.aluracursos.Challenge_LiterAlura.dto.DatosLibro;
import com.aluracursos.Challenge_LiterAlura.model.Autor;
import com.aluracursos.Challenge_LiterAlura.model.Libro;
import com.aluracursos.Challenge_LiterAlura.repository.AutorRepository;
import com.aluracursos.Challenge_LiterAlura.repository.LibroRepository;
import com.aluracursos.Challenge_LiterAlura.service.ConsumoAPI;
import com.aluracursos.Challenge_LiterAlura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    //ATRIBUTES
    @Autowired
    private AutorRepository autorRepositorio;
    @Autowired
    private LibroRepository libroRepositorio;
    private final String baseApiURL = "https://gutendex.com/books/";
    private final String queryApiAux = "?search=";
    private final String spaceApiAux = "%20";
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvierteDatos conversor = new ConvierteDatos();
    private Scanner  teclado = new Scanner(System.in);
    List<DatosAutor> autores = new ArrayList<>();
    private String json;
    private String menu = """
                ************************************************
                    Bienvenido a Literalura
                        Menu de opciones:
                        1 - Buscar Libro por titulo.
                        2 - Spare.
                        0 - Salir.
                ************************************************
                    """;

    //CONSTRUCTORS
    public Principal(){}
    public Principal(AutorRepository autorRepositorio, LibroRepository libroRepositorio){
        this.autorRepositorio = autorRepositorio;
        this.libroRepositorio = libroRepositorio;
    }

    //METHODS
    public void muestraMenu(){
        var opcionElegida = -1;
        while(opcionElegida != 0) {
            System.out.println(menu);

            //System.out.println("Selecciona la opcion deseada");
            opcionElegida = teclado.nextInt();
            teclado.nextLine();

            switch (opcionElegida) {
                case 1 -> buscarLibroPorTitulo();
                case 0 -> opcionElegida = -1;
                default -> System.out.println("opcion no valida");
            }
        }
    }

    private void buscarLibroPorTitulo(){
        DatosLibro datosLibro = obtenerDatosDeAPI();
        if (datosLibro != null){
            Libro libro;
            DatosAutor datosAutor = datosLibro.autor().get(0);

            System.out.println("Libro: " + datosLibro.titulo());
            System.out.println("Autor: " + datosAutor.nombre() +
                    "\n     Nacio en " + datosAutor.fechaNacimiento() +
                    "\n     Murio en " + datosAutor.fechaDefuncion());
            System.out.println("Idioma: " + datosLibro.idiomas().get(0).toUpperCase());
            System.out.println("Descargas: " + datosLibro.numeroDeDescargas());

            System.out.println("Verificando en Base de Datos\n...");

            /*Autor autorExistente = autorRepositorio.findByNombre(datosAutor.nombre());
            if(autorExistente != null){
                libro = new Libro(datosLibro, autorExistente);
            }else{
                Autor nuevoAutor = new Autor(datosAutor);
                libro = new Libro(datosLibro, nuevoAutor);
                autorRepositorio.save(nuevoAutor);
            }
            try{
                libroRepositorio.save(libro);
                System.out.println(libro);
            }catch (Exception e){
                System.out.println("El libro ya existe");
            }*/

        }else{
            System.out.println("Libro no encontrado");
        }
    }

    public DatosLibro obtenerDatosDeAPI() {
        System.out.println("Ingresa el nombre del libro...");
        String userEntry = teclado.nextLine();
        System.out.println("Obteniendo datos de... " + baseApiURL + queryApiAux + userEntry.replace(" ", "+") + spaceApiAux);
        json = consumoAPI.obtenerDatos(baseApiURL + queryApiAux + userEntry.replace(" ", "+") + spaceApiAux);
        DataApiResponse resultadoAPI = conversor.obtenerDatos(json, DataApiResponse.class);

        Optional<DatosLibro> libroBuscado =  resultadoAPI.libros().stream()
                .filter(libro -> libro.titulo().toUpperCase().contains(userEntry.toUpperCase()))
                .findFirst();

        if(libroBuscado.isPresent()){
            return libroBuscado.get();
        }else{
            return null;
        }
    }
}
