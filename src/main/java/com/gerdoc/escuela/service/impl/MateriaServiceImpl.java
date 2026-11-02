package com.gerdoc.escuela.service.impl;

import com.gerdoc.escuela.model.Materia;
import com.gerdoc.escuela.repository.MateriaRepository;
import com.gerdoc.escuela.service.MateriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MateriaServiceImpl implements MateriaService
{
    private final MateriaRepository materiaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Materia> findAll()
    {
        return materiaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Materia> findById(Long id)
    {
        return materiaRepository.findById(id);
    }

    @Override
    @Transactional
    public Materia save(Materia materia)
    {
        return materiaRepository.save(materia);
    }

    @Override
    @Transactional
    public void deleteById(Long id)
    {
        materiaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id)
    {
        return materiaRepository.existsById(id);
    }
}
