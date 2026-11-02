package com.gerdoc.escuela.controller;

import com.gerdoc.escuela.model.Profesor;
import com.gerdoc.escuela.service.ProfesorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Profesores", description = "Operaciones CRUD para la administración de profesores")
@RestController
@RequestMapping("/api/profesores")
@RequiredArgsConstructor
public class ProfesorController
{
    private final ProfesorService profesorService;

    @Operation(summary = "Consultar todos los profesores", description = "Obtiene la lista completa de profesores registrados")
    @GetMapping
    public ResponseEntity<List<Profesor>> findAll()
    {
        List<Profesor> profesores = profesorService.findAll();
        return ResponseEntity.ok(profesores);
    }

    @Operation(summary = "Consultar un profesor por ID", description = "Obtiene un profesor por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Profesor> findById(@PathVariable Long id)
    {
        return profesorService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo profesor", description = "Crea un nuevo profesor en el sistema")
    @PostMapping
    public ResponseEntity<Profesor> create(@RequestBody Profesor profesor)
    {
        profesor.setId(null);
        Profesor nuevoProfesor = profesorService.save(profesor);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoProfesor);
    }

    @Operation(summary = "Actualizar un profesor existente", description = "Actualiza los datos de un profesor existente en el sistema")
    @PutMapping("/{id}")
    public ResponseEntity<Profesor> update(@PathVariable Long id, @RequestBody Profesor profesor)
    {
        return profesorService.findById(id)
                .map(profesorExistente -> {
                    profesorExistente.setNombre(profesor.getNombre());
                    profesorExistente.setEspecialidad(profesor.getEspecialidad());
                    Profesor actualizado = profesorService.save(profesorExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un profesor por ID", description = "Elimina un profesor del sistema por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if (!profesorService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        profesorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}