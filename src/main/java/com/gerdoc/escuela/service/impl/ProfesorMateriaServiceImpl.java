package com.gerdoc.escuela.service.impl;

import com.gerdoc.escuela.model.ProfesorMateria;
import com.gerdoc.escuela.repository.ProfesorMateriaRepository;
import com.gerdoc.escuela.service.ProfesorMateriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfesorMateriaServiceImpl implements ProfesorMateriaService
{
    private final ProfesorMateriaRepository profesormateriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProfesorMateria> findAll()
    {
        return profesormateriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProfesorMateria> findById(Long id)
    {
        return profesormateriaRepository.findById(id);
    }

    @Override
    @Transactional
    public ProfesorMateria save(ProfesorMateria profesormateria)
    {
        return profesormateriaRepository.save(profesormateria);
    }

    @Override
    @Transactional
    public void deleteById(Long id)
    {
        profesormateriaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id)
    {
        return profesormateriaRepository.existsById(id);
    }
}
