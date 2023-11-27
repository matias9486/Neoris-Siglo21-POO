package Model;

import java.util.Objects;

public class Libro {
    private static int id;
    private int libroId;
    private String titulo;
    private String autor;
    private String genero;
    private boolean activo;
    private boolean disponible;

    public Libro(String titulo, String autor, String genero, boolean disponible) {
        id++;
        this.libroId = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.activo = true;
        this.disponible = disponible;
    }

    public Libro(int libroId, String titulo, String autor, String genero, boolean disponible) {
        this.libroId = libroId;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.activo = true;
        this.disponible = disponible;
    }

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Libro libro = (Libro) o;

        if (libroId != libro.libroId) return false;
        if (activo != libro.activo) return false;
        if (disponible != libro.disponible) return false;
        if (!Objects.equals(titulo, libro.titulo)) return false;
        if (!Objects.equals(autor, libro.autor)) return false;
        return Objects.equals(genero, libro.genero);
    }

    @Override
    public int hashCode() {
        int result = libroId;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (autor != null ? autor.hashCode() : 0);
        result = 31 * result + (genero != null ? genero.hashCode() : 0);
        result = 31 * result + (activo ? 1 : 0);
        result = 31 * result + (disponible ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "#"+libroId + ". " + titulo + ".\tGenero: " + genero +".\tAutor: "+ autor +".\tDisponible: " +(disponible?"Si":"No") + ".\tActivo:" + (activo?"Si":"No");
    }
}
