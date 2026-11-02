package com.gerdoc.escuela.service;


import com.gerdoc.escuela.model.ProfesorMateria;

import java.util.List;
import java.util.Optional;

public interface ProfesorMateriaService
{
    // Consultar todos los profesormateria
    List<ProfesorMateria> findAll();
    // Consultar un ProfesorMateria por ID
    Optional<ProfesorMateria> findById(Long id);
    // Guardar o actualizar un ProfesorMateria
    ProfesorMateria save(ProfesorMateria ProfesorMateria);
    // Eliminar un ProfesorMateria por ID
    void deleteById(Long id);
    // Verificar si existe un ProfesorMateria
    boolean existsById(Long id);
}
