package Dao;

import Model.Prestamo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PrestamoDAO implements IPrestamoDAO {
    private List<Prestamo> prestamos;

    public PrestamoDAO() {
        this.prestamos = new ArrayList<>();
    }
    public List<Prestamo> getPrestamos() {
        return prestamos;
    }
    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    //alquilarLibro
    @Override
    public void agregarPrestamo(Prestamo prestamo){
        prestamo.getLibro().setDisponible(false);
        prestamos.add(prestamo);
    }
    //devolverLibro
    @Override
    public void devolverPrestamo(Prestamo prestamo){
        prestamo.getLibro().setDisponible(true);
        prestamo.setFechaDevolucion(LocalDate.now());
        prestamo.setDevuelto(true);
    }

    @Override
    public List<Prestamo> obtenerPrestamosSinDevolver(){
        return prestamos.stream().filter(p -> !p.isDevuelto()).collect(Collectors.toList());
    }
}
