package com.poly.test.service;

import com.poly.test.entities.Defi;
import com.poly.test.repository.DefiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ServiceDefi implements IServiceDefi {

    @Autowired
    private DefiRepository defiRepository;

    @Autowired
    private EmailService emailService; // Injection du service EmailService

    @Override
    public void addDefi(Defi d, MultipartFile mf) throws IOException {
        // Check if the file is not empty and save the image
        if (!mf.isEmpty()) {
            d.setImages(saveImage2(mf));
        }

        // Save the Defi object to the repository
        defiRepository.save(d);

        // Envoi d'un email après l'ajout du defi avec les détails du defi
        emailService.sendDefiAddedEmail(
                d.getEmail(),         // Changed p to d
                d.getNom(),           // Changed p to d
                d.getTitre(),         // Changed p to d
                d.getProblem(),       // Changed p to d
                d.getRetour(),        // Changed p to d
                d.getFirst(),         // Changed p to d
                d.getSecond(),        // Changed p to d
                d.getThird()          // Changed p to d
        );
    }

    @Override
    public void deleteDefi(Long id) {
        defiRepository.deleteById(id);
    }

    @Override
    public void updateDefi(Defi d) {
        // Sauvegarder les modifications du defi d'abord
        defiRepository.save(d);

        // Ensuite, envoyer l'email après la sauvegarde
        emailService.sendDefiModifiedEmail(
                d.getEmail(),
                d.getNom(),
                d.getTitre(),
                d.getValidation()
        );
    }

    @Override
    public List<Defi> getAllDefis() {
        return defiRepository.findAll();
    }

    @Override
    public Page<Defi> getDefisByMc(String mc, Pageable p) {
        return defiRepository.rechercherParMC(mc, p);
    }

    @Override
    public Defi getDefiById(Long id) {
        return defiRepository.findById(id).orElse(null);
    }



    private String giveMeNewName(String oldName) {
        String firstpart = oldName.substring(0, oldName.lastIndexOf("."));
        String secondpart = oldName.substring(oldName.lastIndexOf(".") + 1);
        return firstpart + System.currentTimeMillis() + "." + secondpart;
    }

    @Value("${uploads.dir}")
    private String uploadDir;

    @Override
    public String saveImage2(MultipartFile mf) throws IOException {
        String newName = giveMeNewName(mf.getOriginalFilename());
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectory(uploadPath);
        }
        Path pathFile = uploadPath.resolve(newName);
        Files.write(pathFile, mf.getBytes());
        return newName;
    }

    @Override
    public String saveImage(MultipartFile mf) throws IOException {
        String nomFile = mf.getOriginalFilename();
        String[] tab = nomFile.split("\\.");
        String newName = tab[0] + System.currentTimeMillis() + "." + tab[1];
        File f = new ClassPathResource("static/imagess").getFile();
        String chemin = f.getAbsolutePath();
        Path p = Paths.get(chemin, newName);
        Files.write(p, mf.getBytes());
        return newName;
    }


}
