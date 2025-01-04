package com.poly.test.service;

import com.poly.test.entities.Resultat;
import com.poly.test.repository.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultatService {

    @Autowired
    private ResultatRepository resultaRepository;

    // Sauvegarder un résultat
    public void saveResultat(Resultat resultat) {
        resultaRepository.save(resultat);
    }

    // Liste de tous les résultats
    public List<Resultat> getAllResultats() {
        return resultaRepository.findAll();
    }

    // Obtenir un résultat par ID
    public Resultat getResultatById(Long id) {
        Optional<Resultat> result = resultaRepository.findById(id);
        return result.orElse(null);
    }

    // Mettre à jour un résultat
    public void updateResultat(Resultat resultat) {
        resultaRepository.save(resultat);
    }

    // Supprimer un résultat
    public void deleteResultat(Long id) {
        resultaRepository.deleteById(id);
    }
}


