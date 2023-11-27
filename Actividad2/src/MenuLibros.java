import Model.Biblioteca;
import Model.Libro;
import Model.Prestamo;
import Model.Usuario;

import Exception.LibroNotFoundException;
import Exception.UsuarioNotFoundException;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;


public class MenuLibros {
    public static boolean comprobarTextoVacio(String texto){
        return (texto == null || texto.isEmpty() || texto.trim().isEmpty());
    }

    public static boolean validarDni(String numero) {
        String regex = "^\\d{8}$";
        return numero.matches(regex);
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca("Neoris XXI");
        //Variables de de busquedas
        Libro libroBuscado = null;
        Usuario usuario = null;
        List<Libro> librosBuscados = null;

        //Variables Necesarios para mostrar lista de opciones en los inputDialog
        Object[] possibleValuesLibrosBuscados = null;
        Object selectedValueLibro = null;

        //Variables para controlar los menues
        int opcion = 0;
        int opcionUsuario = 0;
        boolean sesionIniciada = false;

        do{
            try {
                opcionUsuario = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "1. Registrar usuario\n"+
                                "2. Iniciar Sesion\n"+
                                "3. Salir\n", "Super Biblioteca 2023", JOptionPane.QUESTION_MESSAGE));


                switch (opcionUsuario) {
                    case 1://Registrar usuario
                        {
                            //Pedir datos y validar
                            String nombre = JOptionPane.showInputDialog(null,"Ingrese nombre:", "Registrar Usuario", JOptionPane.QUESTION_MESSAGE);
                            if(comprobarTextoVacio(nombre)){
                                throw  new IllegalArgumentException("Nombre es requerido.");
                            }

                            String apellido = JOptionPane.showInputDialog(null,"Ingrese apellido:", "Registrar Usuario", JOptionPane.QUESTION_MESSAGE);
                           if(comprobarTextoVacio(apellido)){
                                throw  new IllegalArgumentException("Apellido es requerido.");
                            }

                            String domicilio = JOptionPane.showInputDialog(null,"Ingrese domicilio:", "Registrar Usuario", JOptionPane.QUESTION_MESSAGE);
                            if(comprobarTextoVacio(domicilio)){
                                throw  new IllegalArgumentException("Domicilio es requerido.");
                            }

                            String dni = JOptionPane.showInputDialog(null,"Ingrese DNI:", "Registrar Usuario", JOptionPane.QUESTION_MESSAGE);
                            if(comprobarTextoVacio(dni)){
                                throw  new IllegalArgumentException("DNI es requerido.");
                            }
                            if(!validarDni(dni)){
                                throw  new IllegalArgumentException("DNI requiere 8 números");
                            }

                            String password = JOptionPane.showInputDialog(null,"Ingrese Password:", "Registrar Usuario", JOptionPane.QUESTION_MESSAGE);
                            if(comprobarTextoVacio(password)){
                                throw  new IllegalArgumentException("Password es requerido.");
                            }

                            //crear usuario
                            Usuario nuevo = new Usuario(nombre, apellido, domicilio, dni,password);
                            biblioteca.getUsuariosDAO().agregarUsuario(nuevo);

                            JOptionPane.showMessageDialog(null, "Usuario agregado con éxito.", "Información ", JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;
                    case 2://Iniciar sesion
                        {
                            String dniLogin = JOptionPane.showInputDialog(null,"Ingrese DNI:", "Iniciar Sesión", JOptionPane.QUESTION_MESSAGE);
                            if(comprobarTextoVacio(dniLogin)){
                                throw  new IllegalArgumentException("Ingrese DNI para iniciar sesión");
                            }
                            if(!validarDni(dniLogin)){
                            throw  new IllegalArgumentException("DNI requiere 8 números");
                        }
                            String passwordLogin = JOptionPane.showInputDialog(null,"Ingrese password:", "Iniciar Sesión", JOptionPane.QUESTION_MESSAGE);
                            if(comprobarTextoVacio(passwordLogin)){
                                throw  new IllegalArgumentException("Ingrese password para iniciar sesión");
                            }
                            usuario = biblioteca.getUsuariosDAO().buscarUsuario(dniLogin,passwordLogin);
                            sesionIniciada = true;

                            if(sesionIniciada){
                                do {
                                    try {
                                        opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                "Menu\n"+
                                                        "1. Agregar libro\n"+
                                                        "2. Buscar libro por titulo\n"+
                                                        "3. Buscar libro por genero\n"+
                                                        "4. Buscar libro por autor\n"+
                                                        "5. Mostrar libros\n"+
                                                        "6. Eliminar libro\n"+

                                                        "7. Registrar nuevo prestamo\n"+
                                                        "8. Registrar devolucion de prestamo\n"+
                                                        "9. Mostrar prestamos\n"+

                                                        "10. Mostrar Usuarios registrados\n"+
                                                        "11. Cerrar sesión\n"+
                                                        "\nElige una opcion","Bienvenido " + usuario.getNombre(),JOptionPane.QUESTION_MESSAGE));

                                        switch (opcion) {
                                            case 1://Agregar libro
                                            {
                                                String titulo =JOptionPane.showInputDialog(null, "Ingrese titulo del libro:","Nuevo Libro",JOptionPane.QUESTION_MESSAGE);
                                                if(comprobarTextoVacio(titulo)){
                                                    throw  new IllegalArgumentException("Ingrese titulo del libro.");
                                                }

                                                String autor = JOptionPane.showInputDialog(null, "Ingrese autor del libro:","Nuevo Libro",JOptionPane.QUESTION_MESSAGE);
                                                if(comprobarTextoVacio(autor)){
                                                    throw  new IllegalArgumentException("Ingrese autor del libro.");
                                                }

                                                String genero = JOptionPane.showInputDialog(null, "Ingrese genero del libro:","Nuevo Libro",JOptionPane.QUESTION_MESSAGE);
                                                if(comprobarTextoVacio(genero)){
                                                    throw  new IllegalArgumentException("Ingrese genero del libro.");
                                                }

                                                boolean disponible = true;
                                                Libro nuevoLibro = new Libro(titulo, autor, genero, disponible);
                                                biblioteca.getLibrosDAO().agregarLibro(nuevoLibro);
                                                JOptionPane.showMessageDialog(null, "Se creó libro con éxito","Información",JOptionPane.INFORMATION_MESSAGE);
                                            }
                                            break;
                                            case 2://Buscar libro por titulo
                                            {
                                                boolean librosDisponibles = biblioteca.getLibrosDAO().getLibros().stream().filter(l -> l.isDisponible()).count()>0;
                                                if(librosDisponibles){
                                                    String tituloBuscar = JOptionPane.showInputDialog(null,"Ingrese titulo del libro:", "Buscar libro por titulo", JOptionPane.QUESTION_MESSAGE);
                                                    if(!comprobarTextoVacio(tituloBuscar)){
                                                        librosBuscados = biblioteca.getLibrosDAO().buscarLibrosPorTitulo(tituloBuscar);
                                                        //elegir de una lista de valores
                                                        possibleValuesLibrosBuscados = librosBuscados.toArray();
                                                        selectedValueLibro = JOptionPane.showInputDialog(null,
                                                                "Seleccione libro", "Libros con el titulo : " + tituloBuscar,
                                                                JOptionPane.INFORMATION_MESSAGE, null,
                                                                possibleValuesLibrosBuscados, possibleValuesLibrosBuscados[0]);

                                                        //si eligio un libro. No cancelo
                                                        if(selectedValueLibro != null){
                                                            libroBuscado = (Libro) selectedValueLibro;
                                                            JOptionPane.showMessageDialog(null, "Selecciono:\n" + libroBuscado.toString(), "Información: ", JOptionPane.INFORMATION_MESSAGE);
                                                        }
                                                    }else{
                                                        throw new IllegalArgumentException("Ingrese un titulo a buscar.");
                                                    }
                                                }else{
                                                    throw new LibroNotFoundException("No se registraron libros aun.");
                                                }

                                            }
                                            break;
                                            case 3://Buscar libro por genero
                                            {
                                                boolean librosDisponibles = biblioteca.getLibrosDAO().getLibros().stream().filter(l -> l.isDisponible()).count()>0;
                                                if(librosDisponibles) {
                                                    String generoBuscar = JOptionPane.showInputDialog(null, "Ingrese genero del libro:", "Buscar libro por genero", JOptionPane.QUESTION_MESSAGE);
                                                    if (!comprobarTextoVacio(generoBuscar)) {
                                                        librosBuscados = biblioteca.getLibrosDAO().buscarLibrosPorGenero(generoBuscar);
                                                        //elegir de una lista de valores
                                                        possibleValuesLibrosBuscados = librosBuscados.toArray();
                                                        selectedValueLibro = JOptionPane.showInputDialog(null,
                                                                "Seleccione libro", "Libros con el genero : " + generoBuscar,
                                                                JOptionPane.INFORMATION_MESSAGE, null,
                                                                possibleValuesLibrosBuscados, possibleValuesLibrosBuscados[0]);

                                                        //si eligio un libro. No cancelo
                                                        if (selectedValueLibro != null) {
                                                            libroBuscado = (Libro) selectedValueLibro;
                                                            JOptionPane.showMessageDialog(null, "Selecciono:\n" + libroBuscado.toString(), "Información: ", JOptionPane.INFORMATION_MESSAGE);
                                                        }
                                                    } else {
                                                        throw new IllegalArgumentException("Ingrese un genero a buscar.");
                                                    }
                                                }else{
                                                    throw new LibroNotFoundException("No se registraon libros aun.");
                                                }
                                            }
                                            break;
                                            case 4://Buscar libro por autor
                                            {
                                                boolean librosDisponibles = biblioteca.getLibrosDAO().getLibros().stream().filter(l -> l.isDisponible()).count()>0;
                                                if(librosDisponibles) {
                                                    String autorBuscar = JOptionPane.showInputDialog(null, "Ingrese autor del libro:", "Buscar libro por autor", JOptionPane.QUESTION_MESSAGE);
                                                    if (!comprobarTextoVacio(autorBuscar)) {
                                                        librosBuscados = biblioteca.getLibrosDAO().buscarLibrosPorAutor(autorBuscar);
                                                        //elegir de una lista de valores
                                                        possibleValuesLibrosBuscados = librosBuscados.toArray();
                                                        selectedValueLibro = JOptionPane.showInputDialog(null,
                                                                "Seleccione libro", "Libros con el autor : " + autorBuscar,
                                                                JOptionPane.INFORMATION_MESSAGE, null,
                                                                possibleValuesLibrosBuscados, possibleValuesLibrosBuscados[0]);

                                                        //si eligio un libro. No cancelo
                                                        if (selectedValueLibro != null) {
                                                            libroBuscado = (Libro) selectedValueLibro;
                                                            JOptionPane.showMessageDialog(null, "Selecciono:\n" + libroBuscado.toString(), "Información: ", JOptionPane.INFORMATION_MESSAGE);
                                                        }
                                                    } else {
                                                        throw new IllegalArgumentException("Ingrese un autor a buscar.");
                                                    }
                                                }else{
                                                    throw new LibroNotFoundException("No se registraron libros aun");
                                                }
                                            }
                                            break;
                                            case 5://mostrar libros
                                            {
                                                String stringLibros = biblioteca.getLibrosDAO().getLibros().stream().map(libro -> libro.toString()).collect(Collectors.joining("\n"));
                                                String msjLibros = stringLibros.isEmpty() ? "No se registraron libros." : ("Libros registrados:\n\n" + stringLibros);
                                                JOptionPane.showMessageDialog(null, msjLibros, "Libros", JOptionPane.INFORMATION_MESSAGE);
                                            }
                                            break;
                                            case 6://eliminar libro
                                            {
                                                //comprobar que haya libro elegido
                                                if (libroBuscado == null) {
                                                    JOptionPane.showMessageDialog(null, "Seleccione primero un libro por titulo, autor o genero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                                } else {
                                                    int confirmacion = JOptionPane.showConfirmDialog(null, "Desea eliminar el siguiente libro ?\n" + libroBuscado, "Eliminar libro:", JOptionPane.YES_NO_OPTION);
                                                    if (confirmacion == 1) {   //1=no 2=yes
                                                        JOptionPane.showMessageDialog(null, "Seleccione otro libro por titulo, autor o genero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                                        //libroBuscado = null;
                                                    } else {
                                                        biblioteca.getLibrosDAO().eliminarLibro((Libro) libroBuscado);
                                                        JOptionPane.showMessageDialog(null, "Se eliminó libro correctamente.", "Información: ", JOptionPane.INFORMATION_MESSAGE);
                                                        //libroBuscado = null;
                                                    }
                                                    libroBuscado = null;
                                                }
                                            }
                                            break;
                                            case 7://Registrar nuevo prestamo
                                            {
                                                //comprobar que haya libro elegido
                                                if (libroBuscado == null) {
                                                    JOptionPane.showMessageDialog(null, "Seleccione primero un libro por titulo, autor o genero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                                } else {
                                                    int confirmacion = JOptionPane.showConfirmDialog(null, "Desea alquilar el siguiente libro ?\n" + libroBuscado, "Registrar prestamo:", JOptionPane.YES_NO_OPTION);
                                                    if(confirmacion == 1) {   //1=no 2=yes
                                                        JOptionPane.showMessageDialog(null, "Seleccione otro libro por titulo, autor o genero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                                        libroBuscado = null;
                                                    } else {
                                                        Prestamo nuevoPrestamo = new Prestamo(libroBuscado, usuario);
                                                        biblioteca.getPrestamosDAO().agregarPrestamo(nuevoPrestamo);
                                                        JOptionPane.showMessageDialog(null, "Se registró prestamo correctamente.", "Información: ", JOptionPane.INFORMATION_MESSAGE);
                                                        libroBuscado = null;
                                                    }
                                                }
                                            }
                                            break;
                                            case 8://registrar devolucion prestamo
                                            {
                                                List<Prestamo> prestamos = biblioteca.getPrestamosDAO().obtenerPrestamosSinDevolver();
                                                if (!prestamos.isEmpty()) {
                                                    //elegir de una lista de valores
                                                    Object[] possibleValuesPrestamos = prestamos.toArray();
                                                    Object selectedValuePrestamo = JOptionPane.showInputDialog(null,
                                                            "Seleccione prestamos a devolver", "Devolver prestamo de libro : ",
                                                            JOptionPane.INFORMATION_MESSAGE, null,
                                                            possibleValuesPrestamos, possibleValuesPrestamos[0]);

                                                    //si eligio un libro. No cancelo
                                                    if (selectedValuePrestamo != null) {
                                                        Prestamo prestamoElegido = (Prestamo) selectedValuePrestamo;
                                                        biblioteca.getPrestamosDAO().devolverPrestamo(prestamoElegido);
                                                        JOptionPane.showMessageDialog(null, "Devolvió: \n" + prestamoElegido.toString(), "Devolución de libro: ", JOptionPane.INFORMATION_MESSAGE);
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "No hay prestamos registrados aun o fueron devueltos.", "Información: ", JOptionPane.INFORMATION_MESSAGE);
                                                }
                                            }
                                            break;
                                            case 9://mostrar prestamos
                                            {
                                                String stringPrestamos = biblioteca.getPrestamosDAO().getPrestamos().stream().map(p -> p.toString()).collect(Collectors.joining("\n"));
                                                String msjPrestamos = stringPrestamos.isEmpty() ? "No se registraron prestamos." : ("Prestamos registrados:\n\n" + stringPrestamos);
                                                JOptionPane.showMessageDialog(null, msjPrestamos, "Prestamos",JOptionPane.INFORMATION_MESSAGE);
                                            }
                                            break;

                                            case 10://Mostrar Usuarios registrados
                                            {
                                                String stringUsuarios = biblioteca.getUsuariosDAO().getUsuarios().stream().map(persona -> persona.toString()).collect(Collectors.joining("\n"));
                                                String msj = stringUsuarios.isEmpty() ? "No se registraron usuarios." : (stringUsuarios);
                                                JOptionPane.showMessageDialog(null, msj, "Usuarios registrados: ", JOptionPane.INFORMATION_MESSAGE);
                                            }
                                            break;

                                            case 11://cerrar sesion
                                            {
                                                JOptionPane.showMessageDialog(null, "Sesión finalizada","Información", JOptionPane.INFORMATION_MESSAGE);
                                                sesionIniciada = false;
                                            }
                                            break;

                                            default:
                                                throw new IllegalArgumentException("Opción incorrecta.");
                                        }
                                    } catch (IllegalArgumentException | LibroNotFoundException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage(), "Se ha producido un Error: ", JOptionPane.ERROR_MESSAGE);
                                    }
                                } while (opcion!= 11);
                            }
                        }
                        break;
                    case 3://Salir
                        {
                            JOptionPane.showMessageDialog(null, "Gracias por utilizar Super Biblioteca 2023 :)","Super Biblioteca 2023", JOptionPane.INFORMATION_MESSAGE);
                        }
                    break;
                    default:
                        throw new IllegalArgumentException("Opción incorrecta.");
                }
            } catch (IllegalArgumentException | UsuarioNotFoundException e ) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Se ha producido un Error: ", JOptionPane.ERROR_MESSAGE);
            }
        } while (opcionUsuario!= 3);
    }
}