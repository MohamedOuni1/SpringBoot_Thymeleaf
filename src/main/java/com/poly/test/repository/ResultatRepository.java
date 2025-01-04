package com.poly.test.repository;

import com.poly.test.entities.Defi;
import com.poly.test.entities.Equipe;
import com.poly.test.entities.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ResultatRepository extends JpaRepository<Resultat, Long> {

}
