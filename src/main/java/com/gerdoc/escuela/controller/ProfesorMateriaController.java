package com.gerdoc.escuela.controller;

import com.gerdoc.escuela.model.ProfesorMateria;
import com.gerdoc.escuela.service.ProfesorMateriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ProfesorMateria", description = "Operaciones CRUD para la administración de profesor-materia")
@RestController
@RequestMapping("/api/profesormateria")
@RequiredArgsConstructor
public class ProfesorMateriaController
{
    private final ProfesorMateriaService profesorMateriaService;

    @Operation(summary = "Consultar todas las asignaciones", description = "Obtiene la lista completa de relaciones profesor-materia registradas")
    @GetMapping
    public ResponseEntity<List<ProfesorMateria>> findAll()
    {
        List<ProfesorMateria> lista = profesorMateriaService.findAll();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Consultar una asignación por ID", description = "Obtiene una relación profesor-materia por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProfesorMateria> findById(@PathVariable Long id)
    {
        return profesorMateriaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva asignación", description = "Crea una nueva relación profesor-materia en el sistema")
    @PostMapping
    public ResponseEntity<ProfesorMateria> create(@RequestBody ProfesorMateria profesorMateria)
    {
        profesorMateria.setId(null);
        ProfesorMateria nuevaAsignacion = profesorMateriaService.save(profesorMateria);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevaAsignacion);
    }

    @Operation(summary = "Actualizar una asignación existente", description = "Actualiza los datos de una relación profesor-materia existente")
    @PutMapping("/{id}")
    public ResponseEntity<ProfesorMateria> update(@PathVariable Long id, @RequestBody ProfesorMateria profesorMateria)
    {
        return profesorMateriaService.findById(id)
                .map(existente -> {
                    existente.setProfesor(profesorMateria.getProfesor());
                    existente.setMateria(profesorMateria.getMateria());
                    ProfesorMateria actualizado = profesorMateriaService.save(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar una asignación por ID", description = "Elimina una relación profesor-materia del sistema por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if (!profesorMateriaService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        profesorMateriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}