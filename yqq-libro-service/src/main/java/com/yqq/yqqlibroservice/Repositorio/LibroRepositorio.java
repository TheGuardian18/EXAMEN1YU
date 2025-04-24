package com.yqq.yqqlibroservice.Repositorio;


import com.yqq.yqqlibroservice.Entidad.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepositorio extends JpaRepository<Libro, Integer> {
}