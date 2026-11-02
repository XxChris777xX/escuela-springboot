package com.gerdoc.escuela.controller;

import com.gerdoc.escuela.model.Materia;
import com.gerdoc.escuela.service.MateriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Materias", description = "Operaciones CRUD para la administración de materias")
@RestController
@RequestMapping("/api/materias")
@RequiredArgsConstructor
public class MateriaController
{
    private final MateriaService materiaService;

    @Operation(summary = "Consultar todas las materias", description = "Obtiene la lista completa de materias registradas")
    @GetMapping
    public ResponseEntity<List<Materia>> findAll()
    {
        List<Materia> materias = materiaService.findAll();
        return ResponseEntity.ok(materias);
    }

    @Operation(summary = "Consultar una materia por ID", description = "Obtiene una materia por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Materia> findById(@PathVariable Long id)
    {
        return materiaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva materia", description = "Crea una nueva materia en el sistema")
    @PostMapping
    public ResponseEntity<Materia> create(@RequestBody Materia materia)
    {
        materia.setId(null);
        Materia nuevaMateria = materiaService.save(materia);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevaMateria);
    }

    @Operation(summary = "Actualizar una materia existente", description = "Actualiza los datos de una materia existente en el sistema")
    @PutMapping("/{id}")
    public ResponseEntity<Materia> update(@PathVariable Long id, @RequestBody Materia materia)
    {
        return materiaService.findById(id)
                .map(materiaExistente -> {
                    materiaExistente.setNombre(materia.getNombre());
                    materiaExistente.setCreditos(materia.getCreditos());
                    Materia actualizado = materiaService.save(materiaExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar una materia por ID", description = "Elimina una materia del sistema por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if (!materiaService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        materiaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}