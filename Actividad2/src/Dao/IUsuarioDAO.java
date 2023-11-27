package Dao;

import Model.Usuario;
import Exception.UsuarioNotFoundException;
public interface IUsuarioDAO {
    void agregarUsuario(Usuario usuario);

    Usuario buscarUsuario(String dni, String password) throws UsuarioNotFoundException;
}
