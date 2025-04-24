package com.yqq.yqqprestamoservice.Controlador;

import com.yqq.yqqprestamoservice.Entidad.Prestamo;
import com.yqq.yqqprestamoservice.Sevice.PrestamoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestamos")
public class PrestamoControlador {

    private final PrestamoService prestamopService;
    private final PrestamoService prestamoService;

    @Autowired
    public PrestamoControlador(PrestamoService prestamopServicio, PrestamoService prestamoService) {
        this.prestamopService = prestamoService;
        this.prestamoService = prestamoService;
    }

    @Operation(summary = "Listar todos los préstamos")
    @ApiResponse(responseCode = "200", description = "Lista de préstamos obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<Prestamo>> listarPrestamos() {
        List<Prestamo> prestamos = prestamopService.getAllPrestamos();
        return new ResponseEntity<>(prestamos, HttpStatus.OK);
    }

    @Operation(summary = "Obtener préstamo por ID con nombre de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Préstamo encontrado"),
            @ApiResponse(responseCode = "404", description = "Préstamo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtenerPrestamoPorId(@PathVariable Integer id) {
        Optional<Prestamo> prestamoOptional = prestamopService.getPrestamoById(id);
        return prestamoOptional.map(prestamo -> {
                    String nombreUsuario = prestamopService.obtenerNombreUsuario(prestamo.getUsuarioId());
                    prestamo.setUsuarioNombre(nombreUsuario);
                    return new ResponseEntity<>(prestamo, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Crear un nuevo préstamo")
    @ApiResponse(responseCode = "201", description = "Préstamo creado exitosamente")
    @PostMapping
    public ResponseEntity<Prestamo> crearPrestamo(@RequestBody Prestamo prestamo) {
        prestamopService.procesarPrestamo(prestamo);
        return new ResponseEntity<>(prestamo, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un préstamo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Préstamo actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Préstamo no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizarPrestamo(@PathVariable Integer id, @RequestBody Prestamo pac) {
        Optional<Prestamo> prestamoExistente = prestamopService.getPrestamoById(id);
        if (prestamoExistente.isPresent()) {
            pac.setId(id);
            Prestamo prestamoGuardado = prestamopService.savePrestamo(pac);
            return new ResponseEntity<>(prestamoGuardado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar un préstamo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Préstamo eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Préstamo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable Integer id) {
        if (prestamopService.getPrestamoById(id).isPresent()) {
            prestamopService.deletePrestamo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Registrar devolución de un préstamo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Devolución registrada"),
            @ApiResponse(responseCode = "404", description = "Préstamo no encontrado")
    })
    @PutMapping("/{id}/devolver")
    public ResponseEntity<Void> devolverPrestamo(@PathVariable Integer id) {
        if (prestamopService.getPrestamoById(id).isPresent()) {
            prestamopService.procesarDevolucion(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtener nombre de usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nombre de usuario obtenido correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<String> obtenerNombreUsuario(@PathVariable Integer usuarioId) {
        String nombre = prestamopService.obtenerNombreUsuario(usuarioId);
        if (nombre != null) {
            return new ResponseEntity<>(nombre, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtener título de un libro por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Título obtenido correctamente"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @GetMapping("/libros/{libroId}/titulo")
    public ResponseEntity<String> obtenerTituloLibro(@PathVariable Integer libroId) {
        String titulo = prestamopService.obtenerTituloLibro(libroId);
        if (titulo != null) {
            return new ResponseEntity<>(titulo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtener préstamo con detalles del usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Préstamo encontrado"),
            @ApiResponse(responseCode = "404", description = "Préstamo no encontrado")
    })
    @GetMapping("/{id}/con-usuario")
    public ResponseEntity<Prestamo> obtenerPrestamoConUsuario(@PathVariable Integer id) {
        Optional<Prestamo> prestamoOptional = prestamopService.obtenerPrestamoConUsuarioPorId(id);
        return prestamoOptional.map(prestamo -> new ResponseEntity<>(prestamo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
