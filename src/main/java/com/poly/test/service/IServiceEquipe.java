package com.poly.test.service;

import com.poly.test.entities.Equipe;
import com.poly.test.entities.Theme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IServiceEquipe {
    // Ajouter une équipe
    void addEquipe(Equipe equipe);

    // Supprimer une équipe par son ID
    void deleteEquipe(Long id);

    // Mettre à jour une équipe
    void updateEquipe(Equipe equipe);

    // Obtenir toutes les équipes
    List<Equipe> getAllEquipes();

    // Obtenir une équipe par son ID
    Equipe getEquipeById(Long id);
}