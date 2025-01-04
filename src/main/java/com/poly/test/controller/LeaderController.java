package com.poly.test.controller;
import com.poly.test.service.ServiceLeader;
import org.springframework.ui.Model;

import com.poly.test.entities.Leader;
import com.poly.test.repository.LeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("leaders")
public class LeaderController {

    @Autowired
    private ServiceLeader leaderService;
    @Autowired
    private LeaderRepository leaderRepository;

    // Liste des leaders
    @GetMapping("/all")
    public String listLeaders(Model model) {
        model.addAttribute("leaders", leaderService.getAllLeaders());
        return "leaders/list";
    }

    // Formulaire pour ajouter un leader
    @GetMapping("/create")
    public String createLeaderForm(Model model) {
        model.addAttribute("leader", new Leader());
        return "leaders/create";
    }

    // Sauvegarder un leader
    @PostMapping("/save")
    public String saveLeader(@ModelAttribute Leader leader) {
        leaderService.saveLeader(leader);
        return "redirect:/leaders/all";
    }


    @GetMapping("/edit/{id}")
    public String editLeader(@PathVariable Long id, Model model) {
        Optional<Leader> leaderOptional = leaderRepository.findById(id);

        // Vérification de la présence du leader
        leaderOptional.ifPresentOrElse(
                leader -> {
                    model.addAttribute("leader", leader);
                    // Continuez avec votre logique ici, par exemple la page d'édition
                },
                () -> {
                    // Gérer le cas où le leader n'est pas trouvé (par exemple, rediriger ou renvoyer une erreur)
                    model.addAttribute("error", "Leader non trouvé");
                    // Rediriger vers la liste des leaders
                }
        );

        return "leader/edit"; // ou une autre vue
    }


    // Sauvegarder les modifications du leader
    @PostMapping("/update")
    public String updateLeader(@ModelAttribute Leader leader) {
        leaderService.saveLeader(leader);
        return "redirect:/leaders/all";
    }

    // Supprimer un leader
    @GetMapping("/delete/{id}")
    public String deleteLeader(@PathVariable Long id) {
        leaderService.deleteLeader(id);
        return "redirect:/leaders/all";
    }
}
