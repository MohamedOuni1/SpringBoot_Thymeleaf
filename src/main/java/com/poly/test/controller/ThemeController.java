package com.poly.test.controller;

import com.poly.test.entities.Theme;  // Changed Theme to Theme
import com.poly.test.repository.ThemeRepository;  // Changed ThemeRepository to ThemeRepository
import com.poly.test.service.IServiceTheme;  // Changed IServiceTheme to IServiceTheme
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/themes")  // Changed /categories to /themes
public class ThemeController {  // Changed CategorieController to ThemeController
    private final ThemeRepository themeRepository;  // Changed categorieRepository to themeRepository
    private IServiceTheme st;  // Changed IServiceTheme to IServiceTheme

    @GetMapping("all")
    public String getAllThemes(@RequestParam(name = "mc", defaultValue = "") String mc, Model m,
                               @RequestParam(name="page", defaultValue = "0") int page,
                               @RequestParam(name="size", defaultValue = "4") int size) {
        Page<Theme> liste = st.getThemesByMc(mc, PageRequest.of(page, size));  // Changed Theme to Theme
        m.addAttribute("themes", liste.getContent());  // Changed categories to themes
        m.addAttribute("pages", new int[liste.getTotalPages()]);
        m.addAttribute("current", liste.getNumber());
        m.addAttribute("mc", mc);
        return "home2";
    }




    @GetMapping("/delete/{id}")
    public String deleteTheme(@PathVariable Long id, Model m) {  // Changed deleteCategorie to deleteTheme
        try {
            st.deleteTheme(id);  // Changed deleteCategorie to deleteTheme
            return "redirect:/themes/all";  // Changed /categories/all to /themes/all
        } catch (IllegalStateException e) {
            m.addAttribute("errorMessage", e.getMessage());
            return "home2";
        }
    }


    @PostMapping("/themes/save")
    public String saveTheme(@ModelAttribute Theme t, Model m) {
        try {
            System.out.println("Tentative d'ajout de thème: " + t.getName());  // Log de débogage
            st.addTheme(t);  // Ajouter le thème
            return "redirect:/themes/all";  // Rediriger vers la liste des thèmes
        } catch (DataIntegrityViolationException e) {
            m.addAttribute("errorMessage", "Un thème avec ce nom existe déjà. Veuillez choisir un nom différent.");
            return "Ajouter_Theme";  // Retourner au formulaire en cas d'erreur
        }
    }



    @GetMapping("/Ajouter_Theme")  // Changed Ajouter_Categorie to Ajouter_Theme
    public String afficherFormulaireAjoutTheme() {  // Changed afficherFormulaireAjoutCategorie to afficherFormulaireAjoutTheme
        return "Ajouter_Theme";  // Changed Ajouter_Categorie to Ajouter_Theme
    }

    @GetMapping("/Update_Theme/{id}")  // Changed Update_Categorie to Update_Theme
    public String afficherFormulaireUpdateTheme(@PathVariable Long id, Model model) {  // Changed afficherFormulaireUpdateCategorie to afficherFormulaireUpdateTheme
        Theme theme = st.getThemeById(id);  // Changed getCategorieById to getThemeById
        model.addAttribute("theme", theme);  // Changed categorie to theme
        return "Update_Theme";  // Changed Update_Categorie to Update_Theme
    }

    @PostMapping("/update")
    public String updateTheme(@ModelAttribute Theme t) {  // Changed updateCategorie to updateTheme, and Theme to Theme
        st.updateTheme(t);  // Changed updateCategorie to updateTheme
        return "redirect:/themes/all";  // Changed /categories/all to /themes/all
    }
}
