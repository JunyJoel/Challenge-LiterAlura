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
import java.util.stream.Collectors;

public class Principal {
    //ATRIBUTES
    @Autowired
    private AutorRepository autorRepositorio;
    @Autowired
    private LibroRepository libroRepositorio;
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
                        2 - Listar libros en DB.
                        3 - Listar autores en DB.
                        4 - Listar autores vivos en determinada fecha.
                        5 - Listar libros por idioma.
                        0 - Salir.
                ************************************************
                    """;
    private String userEntry;

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
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    System.out.println("Lista de libros en base de datos:");
                    obtenerListaDeLibrosEnDB();
                    break;
                case 3:
                    System.out.println("Lista de autores en base de datos:");
                    obtenerListaDeAutoresEnDB();
                    break;
                case 4:
                    //Listar autores vivos en determinada fecha.
                    break;
                case 5:
                    //Listar libros por idioma.
                    break;
                case 0:
                    System.out.println("Hasta luego...");
                    opcionElegida = 0;
                    break;
                default:
                    System.out.println("opcion no valida");
                    break;
            }
        }
    }



    private void buscarLibroPorTitulo(){
        System.out.println("Ingresa el nombre del libro...");
        userEntry = teclado.nextLine();
        //Primero buscamos en DB para ahorrar tiempo de ejecucion.
        Libro libroBuscado = libroRepositorio.findByTituloContainingIgnoreCase(userEntry);
        System.out.println("\nBuscando en Base de Datos\n...\n");

        if(libroBuscado != null){
            System.out.println("Libro encontrado...");
            System.out.println(libroBuscado);
        }else{ //Si no encontramos en DB buscamos en API
            DatosLibro datosLibro = obtenerDatosDeAPI(userEntry);
            if (datosLibro != null){
                Libro libro;
                DatosAutor datosAutor = datosLibro.autor().get(0);

                //Imprimir Libro encontrado
            /*
            System.out.println("Libro: " + datosLibro.titulo());
            System.out.println("Autor: " + datosAutor.nombre() +
                    "\n     Nacio en " + datosAutor.fechaNacimiento() +
                    "\n     Murio en " + datosAutor.fechaDefuncion());
            System.out.println("Idioma: " + datosLibro.idiomas().get(0).toUpperCase());
            System.out.println("Descargas: " + datosLibro.numeroDeDescargas());
            */

                System.out.println("\nVerificando autor en Base de Datos\n...\n");

                Autor autorExistente = autorRepositorio.findByNombre(datosAutor.nombre());
                if(autorExistente != null){
                    libro = new Libro(datosLibro, autorExistente);
                }else{
                    Autor nuevoAutor = new Autor(datosAutor);
                    libro = new Libro(datosLibro, nuevoAutor);
                    autorRepositorio.save(nuevoAutor);
                }
                try{//Si se encontro en API, se guarda en DB y se muestra en pantalla
                    libroRepositorio.save(libro);
                    System.out.println(libro);
                }catch (Exception e){//Si el libro ya esta registrado en la DB
                    System.out.println("El libro ya existe en DB");
                }

            }else{//Si no encontramos en API
                System.out.println("Libro no encontrado en API");
            }
        }
    }

    private void obtenerListaDeLibrosEnDB() {
        List<String> librosEnDB = libroRepositorio.obtenerLibros();
        System.out.println(String.join("\n", librosEnDB));
    }

    private void obtenerListaDeAutoresEnDB(){
        List<String> autoresEnDB = autorRepositorio.obtenerAutores();
        System.out.println(String.join("\n",autoresEnDB));
    }

    public DatosLibro obtenerDatosDeAPI(String busquedaUsuario) {
        //System.out.println("Ingresa el nombre del libro...");
        //userEntry = teclado.nextLine();
        final String baseApiURL = "https://gutendex.com/books/";
        final String queryApiAux = "?search=";
        final String spaceApiAux = "%20";
        System.out.println("\nBuscando en API..." + baseApiURL + queryApiAux + busquedaUsuario.replace(" ", "+") + spaceApiAux + "\n...\n");
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
