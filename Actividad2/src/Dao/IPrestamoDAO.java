package Dao;

import Model.Prestamo;

import java.util.List;

public interface IPrestamoDAO {
    void agregarPrestamo(Prestamo prestamo);

    void devolverPrestamo(Prestamo prestamo);

    List<Prestamo> obtenerPrestamosSinDevolver();
}
