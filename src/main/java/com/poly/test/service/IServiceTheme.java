package com.poly.test.service;

import com.poly.test.entities.Theme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServiceTheme {
    public void addTheme(Theme t);
    public void deleteTheme(Long id);
    public void updateTheme(Theme t);
    public List<Theme> getAllThemes();
    public Theme getThemeById(Long id);
    public Page<Theme> getThemesByMc(String mc, Pageable p);
}
