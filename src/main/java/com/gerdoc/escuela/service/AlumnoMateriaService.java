package com.gerdoc.escuela.service;


import com.gerdoc.escuela.model.AlumnoMateria;

import java.util.List;
import java.util.Optional;

public interface AlumnoMateriaService
{
    // Consultar todos los alumnomateria
    List<AlumnoMateria> findAll();
    // Consultar un AlumnoMateria por ID
    Optional<AlumnoMateria> findById(Long id);
    // Guardar o actualizar un AlumnoMateria
    AlumnoMateria save(AlumnoMateria AlumnoMateria);
    // Eliminar un AlumnoMateria por ID
    void deleteById(Long id);
    // Verificar si existe un AlumnoMateria
    boolean existsById(Long id);
}
