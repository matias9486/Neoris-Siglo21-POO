package Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import Exception.LibroNotFoundException;
import Model.Libro;

public class LibrosDAO implements ILibrosDAO {
    private List<Libro> libros;

    public LibrosDAO() {
        this.libros = new ArrayList<>();
    }
    public List<Libro> getLibros() {
        return libros;
    }
    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }


    /* GESTION LIBROS*/
    //buscar Objeto libro, buscar libro por titulo, genero, autor
    @Override
    public Libro buscarLibro(Libro libro) throws LibroNotFoundException {
        Optional<Libro> buscado = libros.stream().filter(l -> l.getLibroId() == libro.getLibroId()).findFirst();
        if( !buscado.isPresent()){
            throw new LibroNotFoundException("No se encontró libro.");
        }
        return buscado.get();
    }

    //filtrar libros activos y disponibles
    @Override
    public List<Libro> buscarLibrosPorTitulo(String titulo) throws LibroNotFoundException {
        List<Libro> buscado = libros.stream().filter(l -> l.getTitulo().equals(titulo) && l.isDisponible() && l.isActivo()).collect(Collectors.toList());
        if( buscado.isEmpty() ){
            throw new LibroNotFoundException("No se encontraron libros con titulo: " + titulo);
        }
        return buscado;
    }
    @Override
    public List<Libro> buscarLibrosPorGenero(String genero) throws LibroNotFoundException {
        List<Libro> buscado = libros.stream().filter(l -> l.getGenero().equals(genero) && l.isDisponible() && l.isActivo()).collect(Collectors.toList());
        if( buscado.isEmpty() ){
            throw new LibroNotFoundException("No se encontraron libros con genero: " + genero);
        }
        return buscado;
    }
    @Override
    public List<Libro> buscarLibrosPorAutor(String autor) throws LibroNotFoundException {
        List<Libro> buscado = libros.stream().filter(l -> l.getAutor().equals(autor) && l.isDisponible() && l.isActivo()).collect(Collectors.toList());
        if( buscado.isEmpty() ){
            throw new LibroNotFoundException("No se encontraron libros con autor: " + autor);
        }
        return buscado;
    }

    //agregarLibro
    @Override
    public void agregarLibro(Libro libro){
        libros.add(libro);
    }

    ///eliminarLibro
    @Override
    public void eliminarLibro(Libro eliminar) throws LibroNotFoundException {
        Libro buscado = buscarLibro(eliminar);
        buscado.setActivo(false);
    }

}
