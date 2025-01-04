package com.poly.test.controller;
import com.poly.test.entities.Resultat;
import com.poly.test.service.ResultatService;
import org.springframework.ui.Model;

import com.poly.test.service.ServiceDefi;
import com.poly.test.service.ServiceEquipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("resultats/")
public class ResultatController {

    @Autowired
    private ResultatService resultatService;

    @Autowired
    private ServiceDefi defiService;

    @Autowired
    private ServiceEquipe equipeService;

    // Page pour ajouter un résultat
    @GetMapping("/add")
    public String showAddResultatPage(Model model) {
        model.addAttribute("defis", defiService.getAllDefis());
        model.addAttribute("equipes", equipeService.getAllEquipes());
        model.addAttribute("resultat", new Resultat()); // Nouveau résultat
        return "resultats/add";
    }

    // Enregistrer un résultat
    @PostMapping("/save")
    public String saveResultat(@ModelAttribute Resultat resultat) {
        resultatService.saveResultat(resultat);
        return "redirect:/resultats/all"; // Rediriger vers la liste des résultats
    }

    // Liste des résultats
    @GetMapping("/all")
    public String listResultats(Model model) {
        model.addAttribute("resultats", resultatService.getAllResultats());
        return "resultats/list";
    }

    // Page pour éditer un résultat
    @GetMapping("/edit/{id}")
    public String showEditResultatPage(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Resultat resultat = resultatService.getResultatById(id);

        if (resultat == null) {
            redirectAttributes.addFlashAttribute("error", "Le résultat avec l'ID " + id + " n'existe pas.");
            return "redirect:/resultats/all";
        }

        model.addAttribute("resultat", resultat);
        model.addAttribute("defis", defiService.getAllDefis());
        model.addAttribute("equipes", equipeService.getAllEquipes());

        return "resultats/edit";
    }


    // Mettre à jour un résultat
    @PostMapping("/update")
    public String updateResultat(@ModelAttribute Resultat resultat) {
        resultatService.saveResultat(resultat);
        return "redirect:/resultats/all";
    }

    // Supprimer un résultat
    @PostMapping("/delete/{id}")
    public String deleteResultat(@PathVariable Long id) {
        resultatService.deleteResultat(id);
        return "redirect:/resultats/all";
    }
}