package com.poly.test.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Defi {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String titre;
    private String email;
    private int tel;
    private String problem;
    private String retour;
    private String first;
    private String second;
    private String third;
    private String validation="Pas Encore";

    private String images;
    //private String imageUrl;
    @ManyToOne
    private Theme theme;
    @OneToMany(mappedBy = "defi")
    private Set<Resultat> resultats; // Liste des résultats associés à ce défi
}
