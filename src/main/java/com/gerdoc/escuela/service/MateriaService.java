package com.gerdoc.escuela.service;


import com.gerdoc.escuela.model.Materia;

import java.util.List;
import java.util.Optional;

public interface MateriaService
{
    // Consultar todos los materias
    List<Materia> findAll();
    // Consultar un materia por ID
    Optional<Materia> findById(Long id);
    // Guardar o actualizar un materia
    Materia save(Materia materia);
    // Eliminar un materia por ID
    void deleteById(Long id);
    // Verificar si existe un materia
    boolean existsById(Long id);
}
