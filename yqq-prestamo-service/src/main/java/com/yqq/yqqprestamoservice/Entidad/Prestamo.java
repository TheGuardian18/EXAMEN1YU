package com.yqq.yqqprestamoservice.Entidad;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Map;

@Entity
//@Data // Puedes usar Lombok para generar getters, setters, equals, hashCode y toString automáticamente
public class Prestamo {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public Map<Integer, Integer> getLibrosPrestados() {
        return librosPrestados;
    }

    public void setLibrosPrestados(Map<Integer, Integer> librosPrestados) {
        this.librosPrestados = librosPrestados;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public LocalDate getFechaDevuelto() {
        return fechaDevuelto;
    }

    public void setFechaDevuelto(LocalDate fechaDevuelto) {
        this.fechaDevuelto = fechaDevuelto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Prestamo(Integer id, Integer usuarioId, String usuarioNombre, Map<Integer, Integer> librosPrestados, LocalDate fechaPrestamo, LocalDate fechaDevolucion, LocalDate fechaDevuelto, String estado) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.librosPrestados = librosPrestados;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaDevuelto = fechaDevuelto;
        this.estado = estado;
    }


    public Prestamo() {
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", usuarioNombre='" + usuarioNombre + '\'' +
                ", librosPrestados=" + librosPrestados +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                ", fechaDevuelto=" + fechaDevuelto +
                ", estado='" + estado + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer usuarioId; // ID del usuario que realiza el préstamo

    private String usuarioNombre; // Nombre del usuario al momento del préstamo

    @ElementCollection
    @CollectionTable(name = "prestamo_libros", joinColumns = @JoinColumn(name = "prestamo_id"))
    @MapKeyColumn(name = "libro_id") // La clave del mapa será el ID del libro
    @Column(name = "cantidad") // El valor del mapa será la cantidad prestada de ese libro
    private Map<Integer, Integer> librosPrestados; // Mapa de [ID del libro : Cantidad prestada]

    @Column(nullable = false)
    private LocalDate fechaPrestamo;

    private LocalDate fechaDevolucion; // Fecha esperada de devolución

    private LocalDate fechaDevuelto; // Fecha real de devolución (puede ser null si aún no se devuelve)

    private String estado;
}