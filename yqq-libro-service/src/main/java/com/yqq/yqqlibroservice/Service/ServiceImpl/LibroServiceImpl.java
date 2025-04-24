package com.yqq.yqqlibroservice.Service.ServiceImpl;

import com.yqq.yqqlibroservice.Entidad.Libro;
import com.yqq.yqqlibroservice.Repositorio.LibroRepositorio;
import com.yqq.yqqlibroservice.Service.LibroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Override
    public List<Libro> listar() {
        return libroRepositorio.findAll();
    }

    @Override
    public Libro guardar(Libro libro) {
        return libroRepositorio.save(libro);
    }

    @Override
    public Libro actualizar(Libro libro) {
        return libroRepositorio.save(libro);
    }

    @Override
    public Optional<Libro> listarPorId(Integer id) {
        return libroRepositorio.findById(id);
    }

    @Override
    public void eliminarPorId(Integer id) {
        libroRepositorio.deleteById(id);
    }

    @Override
    @Transactional
    public void decrementarStockLibro(Integer libroId, Integer cantidad) {
        Optional<Libro> libroOptional = libroRepositorio.findById(libroId);
        if (libroOptional.isPresent()) {
            Libro libro = libroOptional.get();
            if (libro.getStock() >= cantidad) {
                libro.setStock(libro.getStock() - cantidad);
                libroRepositorio.save(libro);
            } else {
                throw new RuntimeException("No hay suficiente stock para reducir del libro con ID: " + libroId);
            }
        } else {
            throw new RuntimeException("No se encontró el libro con ID: " + libroId);
        }
    }

    @Override
    public Optional<Libro> obtenerLibroPorId(Integer id) {
        return libroRepositorio.findById(id);
    }
}