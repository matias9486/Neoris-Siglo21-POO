package Dao;

import Model.Libro;

import java.util.List;
import Exception.LibroNotFoundException;

public interface ILibrosDAO {
    /* GESTION LIBROS*/
    //buscar Objeto libro, buscar libro por titulo, genero, autor
    Libro buscarLibro(Libro libro) throws LibroNotFoundException;

    //filtrar libros activos y disponibles
    List<Libro> buscarLibrosPorTitulo(String titulo) throws LibroNotFoundException;

    List<Libro> buscarLibrosPorGenero(String genero) throws LibroNotFoundException;

    List<Libro> buscarLibrosPorAutor(String autor) throws LibroNotFoundException;

    //agregarLibro
    void agregarLibro(Libro libro);

    ///eliminarLibro
    void eliminarLibro(Libro eliminar) throws LibroNotFoundException;
}
