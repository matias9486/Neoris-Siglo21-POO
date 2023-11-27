package Model;

import Dao.LibrosDAO;
import Dao.PrestamoDAO;
import Dao.UsuarioDAO;
public class Biblioteca {
    private String nombre;
    private LibrosDAO librosDAO;
    private UsuarioDAO usuariosDAO;
    private PrestamoDAO prestamosDAO;

    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.librosDAO = new LibrosDAO();
        this.usuariosDAO = new UsuarioDAO();
        this.prestamosDAO = new PrestamoDAO();
        //agregar usuario al crear biblioteca
        usuariosDAO.agregarUsuario( new Usuario("Matias", "Alancay", "Sarasa 123", "32104501","1234"));
    }
    public LibrosDAO getLibrosDAO() {
        return librosDAO;
    }
    public UsuarioDAO getUsuariosDAO() {
        return usuariosDAO;
    }

    public PrestamoDAO getPrestamosDAO() {
        return prestamosDAO;
    }
}
