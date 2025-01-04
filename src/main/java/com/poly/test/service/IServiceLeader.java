package com.poly.test.service;

import com.poly.test.entities.Leader;

import java.util.List;

public interface IServiceLeader {
    // Ajouter un leader
    void addLeader(Leader leader);

    // Supprimer un leader par son ID
    void deleteLeader(Long id);

    // Mettre à jour un leader
    void updateLeader(Leader leader);

    // Obtenir tous les leaders
    List<Leader> getAllLeaders();

    // Obtenir un leader par son ID
    Leader getLeaderById(Long id);
}