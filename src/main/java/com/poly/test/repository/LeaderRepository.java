package com.poly.test.repository;

import com.poly.test.entities.Leader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface LeaderRepository extends JpaRepository<Leader, Long> {
    Optional<Leader> findByNomComplet(String nomComplet);

}