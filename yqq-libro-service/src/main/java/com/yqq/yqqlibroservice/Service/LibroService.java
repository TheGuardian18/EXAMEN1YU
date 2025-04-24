package com.yqq.yqqlibroservice.Service;

import com.yqq.yqqlibroservice.Entidad.Libro;


import java.util.List;
import java.util.Optional;

public interface LibroService {
    List<Libro> listar();
    Libro guardar(Libro libro);
    Libro actualizar(Libro libro);
    Optional<Libro> listarPorId(Integer id);
    void eliminarPorId(Integer id);
    void decrementarStockLibro(Integer id, Integer cantidad);
    Optional<Libro> obtenerLibroPorId(Integer id); // Cambiado a Optional<Libro>
}