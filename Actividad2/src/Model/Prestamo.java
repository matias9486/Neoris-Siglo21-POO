package Model;

import java.time.LocalDate;

public class Prestamo {
    private static int id;
    private int prestamoId;
    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaAlquiler;
    private boolean devuelto;
    private LocalDate fechaDevolucion;

    public Prestamo(Libro libro, Usuario usuario) {
        id++;
        this.prestamoId = id;
        this.libro = libro;
        this.usuario = usuario;
        this.fechaAlquiler = LocalDate.now();
        this.devuelto = false;
    }

    public Prestamo(int prestamoId, Libro libro, Usuario usuario, LocalDate fechaAlquiler, boolean devuelto, LocalDate fechaDevolucion) {
        this.prestamoId = prestamoId;
        this.libro = libro;
        this.usuario = usuario;
        this.fechaAlquiler = fechaAlquiler;
        this.devuelto = devuelto;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(int prestamoId) {
        this.prestamoId = prestamoId;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(LocalDate fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        return "Prestamo #" + prestamoId  + "\n\t" + libro.toString() + ".\n\t" + usuario.toString() +
                ".\n\tFecha Alquiler: " + fechaAlquiler.toString() + ".\n\tDevuelto: " + (devuelto?"Si":"No") + "\n\n";
    }
}
