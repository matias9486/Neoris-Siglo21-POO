package Model;

public class Usuario {
    private static int id =0;
    private int usuarioId;
    private String nombre;
    private String apellido;
    private String domicilio;
    private String dni;

    private String password;

    public Usuario(String nombre, String apellido, String domicilio, String dni, String password) {
        id++;
        this.usuarioId = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.password = password;
    }
    public Usuario(int usuarioId, String nombre, String apellido, String domicilio, String dni, String password) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.password = password;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "#"+usuarioId+ ". " + apellido +", " + nombre +".\tDni: " + dni;
    }
}
