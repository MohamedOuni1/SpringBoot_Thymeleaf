package com.poly.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    public void sendDefiAddedEmail(String toEmail, String nom, String titre,
                                   String problem, String retour, String first, String second, String third) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Défi ajouté avec succès !");

        // Corps de l'email avec les informations détaillées
        String emailContent = "Bonjour " + nom + ",\n\n" +
                "Nous vous remercions chaleureusement d'avoir ajouté un nouveau défi sur notre plateforme !\n" +
                "Voici les détails du défi que vous avez ajouté :\n\n" +
                "- Titre du défi : " + titre  + "\n" +
                "- Problématique du défi : " + problem + "\n" +
                "- Resultat souhaité : " + retour + "\n" +
                "- Premier prix : " + first + "\n" +
                "- Deuxième prix : " + second + "\n\n" +
                "- Troisième prix : " + third + "\n\n" +
                "Si vous avez besoin de plus d'informations ou souhaitez apporter des modifications à votre défi, n'hésitez pas à nous contacter.\n\n" +
                "Cordialement,\nL'équipe de Réveillon de l'Info.";

        message.setText(emailContent);
        mailSender.send(message);
    }


    public void sendDefiModifiedEmail(String toEmail, String nom, String titre, String validation) {
        // Ne rien faire si le statut est "En attente"
        if ("Pas Encore".equals(validation)) {
            return; // Pas d'email à envoyer
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Défi de Réveillon de l'info !");

        // Définir le texte en fonction du statut de validation
        String statusMessage;
        switch (validation) {
            case "Accepté":
                statusMessage = "Votre défi '" + titre + "' a été validé avec succès.";
                break;
            default:
                statusMessage = "Nous regrettons de vous informer que votre défi '" + titre + "' n'a pas été validé. Veuillez vérifier les informations soumises et tenter à nouveau.";
                break;
        }

        // Créer le contenu de l'email
        String emailContent = statusMessage + "\n\n" +
                "Si vous avez des questions ou besoin d'assistance, n'hésitez pas à nous contacter.\n\n" +
                "Nous vous remercions pour votre participation et espérons vous retrouver lors de nos prochains événements.\n\n" +
                "Cordialement,\nL'équipe de Réveillon de l'Info.";

        message.setText(emailContent);
        mailSender.send(message);
    }




}
