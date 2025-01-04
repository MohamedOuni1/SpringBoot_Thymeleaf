package com.poly.test.repository;

import com.poly.test.entities.Theme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository <Theme,Long> {
    @Query("select t from Theme t where t.name like %:x%")
    public Page<Theme> rechercherParMC(@Param("x") String mc, Pageable p);

}
