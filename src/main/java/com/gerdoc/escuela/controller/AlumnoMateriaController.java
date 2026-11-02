package com.gerdoc.escuela.controller;

import com.gerdoc.escuela.model.AlumnoMateria;
import com.gerdoc.escuela.service.AlumnoMateriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "AlumnoMateria", description = "Operaciones CRUD para la administración de alumno-materia")
@RestController
@RequestMapping("/api/alumnomateria")
@RequiredArgsConstructor
public class AlumnoMateriaController
{
    private final AlumnoMateriaService alumnoMateriaService;

    @Operation(summary = "Consultar todas las asignaciones", description = "Obtiene la lista completa de relaciones alumno-materia registradas")
    @GetMapping
    public ResponseEntity<List<AlumnoMateria>> findAll()
    {
        List<AlumnoMateria> lista = alumnoMateriaService.findAll();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Consultar una asignación por ID", description = "Obtiene una relación alumno-materia por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<AlumnoMateria> findById(@PathVariable Long id)
    {
        return alumnoMateriaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva asignación", description = "Crea una nueva relación alumno-materia en el sistema")
    @PostMapping
    public ResponseEntity<AlumnoMateria> create(@RequestBody AlumnoMateria alumnoMateria)
    {
        alumnoMateria.setId(null);
        AlumnoMateria nuevaAsignacion = alumnoMateriaService.save(alumnoMateria);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevaAsignacion);
    }

    @Operation(summary = "Actualizar una asignación existente", description = "Actualiza los datos de una relación alumno-materia existente")
    @PutMapping("/{id}")
    public ResponseEntity<AlumnoMateria> update(@PathVariable Long id, @RequestBody AlumnoMateria alumnoMateria)
    {
        return alumnoMateriaService.findById(id)
                .map(existente -> {
                    existente.setAlumno(alumnoMateria.getAlumno());
                    existente.setMateria(alumnoMateria.getMateria());
                    AlumnoMateria actualizado = alumnoMateriaService.save(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar una asignación por ID", description = "Elimina una relación alumno-materia del sistema por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if (!alumnoMateriaService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        alumnoMateriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}