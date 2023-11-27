package Dao;

import Model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Exception.UsuarioNotFoundException;
public class UsuarioDAO implements IUsuarioDAO {
    private List<Usuario> usuarios;

    public UsuarioDAO() {
        this.usuarios = new ArrayList<>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    //----------- GESTION USUARIOS------------
    //registrarUsuario
    @Override
    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    @Override
    public Usuario buscarUsuario(String dni, String password) throws UsuarioNotFoundException {
        Optional<Usuario> buscado = usuarios.stream().filter(u -> u.getDni().equals(dni) && u.getPassword().equals(password)).findFirst();
        if(!buscado.isPresent())
            throw new UsuarioNotFoundException("No se encontro usuario con dni y password ingresados");
        return buscado.get();
    }
}
