package com.poly.test.controller;

import com.poly.test.entities.Theme;
import com.poly.test.entities.Defi;  // Changed Defi to Defi
import com.poly.test.repository.DefiRepository;  // Changed DefiRepository to DefiRepository
import com.poly.test.service.IServiceDefi;  // Changed IServiceDefi to IServiceDefi
import com.poly.test.service.IServiceTheme;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/defis")
public class DefiController {
    private final DefiRepository defiRepository;
    private IServiceDefi sd;
    private final IServiceTheme st;

    @GetMapping("/all")
    public String getAllDefis(@RequestParam(name = "mc", defaultValue = "") String mc, Model m,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "6") int size) {
        Page<Defi> liste = sd.getDefisByMc(mc, PageRequest.of(page, size));
        m.addAttribute("defis", liste.getContent());
        m.addAttribute("pages", new int[liste.getTotalPages()]);
        m.addAttribute("current", liste.getNumber());
        m.addAttribute("mc", mc);
        return "home";
    }

    @GetMapping("/delete/{id}")
    public String deleteDefi(@PathVariable Long id, Model m) {
        sd.deleteDefi(id);
        return "redirect:/defis/all";
    }

    @PostMapping("/save")
    public String saveDefi(@ModelAttribute Defi d, Model m, @RequestParam("file") MultipartFile file) throws IOException {  // Changed Defi to Defi
        try {
            sd.addDefi(d, file);  // Changed addProduit to addDefi
            return "redirect:/defis/all";  // Changed /produits to /defis
        } catch (DataIntegrityViolationException e) {
            List<Theme> themes = st.getAllThemes();
            m.addAttribute("themes", themes);
            m.addAttribute("defi", d);
            m.addAttribute("errorMessage", "Un defi avec ce nom existe déjà. Veuillez choisir un nom différent.");
            return "Ajouter_Defi";
        }
    }

    @GetMapping("/Ajouter_Defi")
    public String afficherFormulaireAjoutDefi(Model m) {
        List<Theme> themes = st.getAllThemes();
        m.addAttribute("themes", themes);
        return "Ajouter_Defi";
    }

    @GetMapping("/Update_Defi/{id}")
    public String afficherFormulaireUpdateDefi(@PathVariable Long id, Model model) {
        Defi defi = sd.getDefiById(id);
        List<Theme> themes = st.getAllThemes();
        model.addAttribute("defi", defi);  //
        model.addAttribute("themes", themes);
        return "Update_Defi";
    }

    @PostMapping("/update")
    public String updateDefi(@ModelAttribute Defi d, @RequestParam("image") MultipartFile imageFile) throws IOException {  // Changed Defi to Defi
        Defi existingDefi = sd.getDefiById(d.getId());
        if (existingDefi == null) {
            throw new RuntimeException("Defi non trouvé");
        }
        if (!imageFile.isEmpty()) {
            String newImageName = sd.saveImage2(imageFile);
            d.setImages(newImageName);
        } else {
            d.setImages(existingDefi.getImages());
        }
        sd.updateDefi(d);

        return "redirect:/defis/all";
    }
    @GetMapping("/details/{id}")
    public String showDefiDetails(@PathVariable("id") Long id, Model model) {
        Defi defi = sd.getDefiById(id);
        model.addAttribute("defi", defi);
        return "show";
    }

}
