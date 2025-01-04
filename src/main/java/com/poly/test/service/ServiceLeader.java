package com.poly.test.service;


import com.poly.test.entities.Leader;
import com.poly.test.repository.LeaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceLeader {

    private final LeaderRepository leaderRepository;

    public ServiceLeader(LeaderRepository leaderRepository) {
        this.leaderRepository = leaderRepository;
    }

    public List<Leader> getAllLeaders() {
        return leaderRepository.findAll();
    }

    public Optional<Leader> getLeaderById(Long id) {
        return leaderRepository.findById(id);
    }

    public boolean existsByNomComplet(String nomComplet) {
        return leaderRepository.findByNomComplet(nomComplet).isPresent();
    }

    public Leader saveLeader(Leader leader) {
        return leaderRepository.save(leader);
    }

    public void deleteLeader(Long id) {
        leaderRepository.deleteById(id);
    }
}
