package com.poly.test.service;

import com.poly.test.entities.Equipe;
import com.poly.test.repository.EquipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceEquipe {

    private final EquipeRepository equipeRepository;

    public ServiceEquipe(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();
    }

    public Optional<Equipe> getEquipeById(Long id) {
        return equipeRepository.findById(id);
    }

    public Equipe saveEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    public void deleteEquipe(Long id) {
        equipeRepository.deleteById(id);
    }
}