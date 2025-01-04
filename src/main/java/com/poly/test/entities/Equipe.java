package com.poly.test.entities;


import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String nomj1;
    private String nomj2;

    @OneToOne(cascade = CascadeType.PERSIST)  // Vous pouvez ajuster CascadeType en fonction de vos besoins
    @JoinColumn(name = "leader_id", referencedColumnName = "id")
    private Leader leader;


    @OneToMany(mappedBy = "equipe1")
    private Set<Resultat> resultats1; // Résultats où cette équipe est la première équipe

    @OneToMany(mappedBy = "equipe2")
    private Set<Resultat> resultats2; // Résultats où cette équipe est la deuxième équipe

    @OneToMany(mappedBy = "equipe3")
    private Set<Resultat> resultats3; // Résultats où cette équipe est la troisième équipe

    // Constructeur sans argument, nécessaire pour JPA
    public Equipe() {
    }


    // Getter et Setter pour id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter et Setter pour nom
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getter et Setter pour nomj1
    public String getNomj1() {
        return nomj1;
    }

    public void setNomj1(String nomj1) {
        this.nomj1 = nomj1;
    }

    // Getter et Setter pour nomj2
    public String getNomj2() {
        return nomj2;
    }

    public void setNomj2(String nomj2) {
        this.nomj2 = nomj2;
    }

    // Getter et Setter pour leader
    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }


    public Set<Resultat> getResultats1() {
        return resultats1;
    }

    public void setResultats1(Set<Resultat> resultats1) {
        this.resultats1 = resultats1;
    }

    public Set<Resultat> getResultats2() {
        return resultats2;
    }

    public void setResultats2(Set<Resultat> resultats2) {
        this.resultats2 = resultats2;
    }

    public Set<Resultat> getResultats3() {
        return resultats3;
    }

    public void setResultats3(Set<Resultat> resultats3) {
        this.resultats3 = resultats3;
    }

}
