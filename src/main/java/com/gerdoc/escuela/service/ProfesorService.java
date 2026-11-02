package com.gerdoc.escuela.service;


import com.gerdoc.escuela.model.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorService
{
    // Consultar todos los profesors
    List<Profesor> findAll();
    // Consultar un profesor por ID
    Optional<Profesor> findById(Long id);
    // Guardar o actualizar un profesor
    Profesor save(Profesor profesor);
    // Eliminar un profesor por ID
    void deleteById(Long id);
    // Verificar si existe un profesor
    boolean existsById(Long id);
}
