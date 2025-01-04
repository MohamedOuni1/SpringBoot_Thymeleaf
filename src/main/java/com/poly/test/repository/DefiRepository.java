package com.poly.test.repository;

import com.poly.test.entities.Defi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DefiRepository extends JpaRepository<Defi,Long> {

    //public List<Defi> findByNomContains(String mc);


    @Query ("select d from Defi d where d.nom like %:x%")
    public Page<Defi> rechercherParMC(@Param("x") String mc, Pageable p);
}
