package com.yqq.yqqprestamoservice.Repositorio;

import com.yqq.yqqprestamoservice.Entidad.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.time.LocalDate;


public interface PrestamoRepositorio extends JpaRepository<Prestamo, Integer> {
    int countByUsuarioIdAndEstado(Integer usuarioId, String estado);

    List<Prestamo> findByUsuarioId(Integer usuarioId);

    List<Prestamo> findByEstado(String estado);

    List<Prestamo> findByFechaDevolucionBeforeAndFechaDevueltoIsNull(LocalDate fechaDevolucion);

    List<Prestamo> findByUsuarioIdAndEstado(Integer usuarioId, String estado);
}