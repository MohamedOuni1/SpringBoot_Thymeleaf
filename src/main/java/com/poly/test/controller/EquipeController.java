package com.poly.test.controller;
import com.poly.test.entities.Theme;
import com.poly.test.service.ServiceEquipe;
import com.poly.test.service.ServiceLeader;
import org.springframework.ui.Model;
import com.poly.test.entities.Equipe;
import com.poly.test.entities.Leader;
import com.poly.test.repository.EquipeRepository;
import com.poly.test.repository.LeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/equipes")
public class EquipeController {

    @Autowired
    private ServiceEquipe equipeService;
    private ServiceLeader leaderService;
    @Autowired
    private ServiceEquipe serviceEquipe;

    @GetMapping("/all")
    public String listEquipes(Model model) {
        model.addAttribute("equipes", equipeService.getAllEquipes());
        return "equipes/list";
    }

    @GetMapping("/create")
    public String createEquipeForm(Model model) {
        model.addAttribute("equipe", new Equipe());
        return "equipes/create";
    }


    @PostMapping("/save")
    public String saveEquipe(@ModelAttribute Equipe equipe) {
        equipeService.saveEquipe(equipe);
        return "redirect:/equipes/all";
    }


    @GetMapping("/edit/{id}")
    public String editEquipe(@PathVariable Long id, Model model) {
        // Vérification si equipeService est null
        if (equipeService == null) {
            throw new IllegalStateException("equipeService n'a pas été injecté correctement.");
        }
        Optional<Equipe> equipeOptional = equipeService.getEquipeById(id);

        if (equipeOptional.isPresent()) {
            Equipe equipe = equipeOptional.get();
            model.addAttribute("equipe", equipe);  // Ajouter l'équipe au modèle
            return "equipes/edit";  // Vue pour éditer l'équipe
        } else {
            // Ajouter un message d'erreur pour afficher à l'utilisateur
            model.addAttribute("error", "L'équipe avec l'ID " + id + " n'a pas été trouvée.");
            return "redirect:/equipes/all";  // Rediriger vers la liste des équipes
        }
    }



    @PostMapping("/update")
    public String updateEquipe(@ModelAttribute Equipe equipe) {
        equipeService.saveEquipe(equipe);
        return "redirect:/equipes/all";
    }

    @PostMapping("/delete/{id}")
    public String deleteEquipe(@PathVariable Long id) {
        equipeService.deleteEquipe(id);  // Appel à la méthode de suppression
        return "redirect:/equipes/all";  // Redirection vers la liste des équipes
    }

}
