package com.poly.test.service;

import com.poly.test.entities.Theme;
import com.poly.test.repository.ThemeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ServiceTheme implements IServiceTheme {

    private final ThemeRepository themeRepository;  // Changed categorieRepository to themeRepository

    @Override
    public void addTheme(Theme t) {  // Changed addCategorie to addTheme
        themeRepository.save(t);
    }

    @Override
    public void deleteTheme(Long id) {  // Changed deleteCategorie to deleteTheme
        themeRepository.deleteById(id);
    }

    @Override
    public void updateTheme(Theme t) {  // Changed updateCategorie to updateTheme
        themeRepository.save(t);
    }

    @Override
    public List<Theme> getAllThemes() {  // Changed getAllCategories to getAllThemes
        return themeRepository.findAll();
    }

    @Override
    public Theme getThemeById(Long id) {  // Changed getCategorieById to getThemeById
        Optional<Theme> theme = themeRepository.findById(id);
        return theme.orElse(null);  // Retourne null si le theme n'est pas trouvé
    }

    @Override
    public Page<Theme> getThemesByMc(String mc, Pageable p) {  // Changed getCategoriesByMc to getThemesByMc
        return themeRepository.rechercherParMC(mc, p);
    }
}
