package com.poly.test.entities;

import jakarta.persistence.*;

@Entity
public class Resultat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "defi_id")
    private Defi defi;  // Le défi associé

    @ManyToOne
    @JoinColumn(name = "equipe_1_id")
    private Equipe equipe1;  // Première équipe gagnante

    @ManyToOne
    @JoinColumn(name = "equipe_2_id")
    private Equipe equipe2;  // Deuxième équipe gagnante

    @ManyToOne
    @JoinColumn(name = "equipe_3_id")
    private Equipe equipe3;  // Troisième équipe gagnante





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Defi getDefi() {
        return defi;
    }

    public void setDefi(Defi defi) {
        this.defi = defi;
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
    }

    public Equipe getEquipe3() {
        return equipe3;
    }

    public void setEquipe3(Equipe equipe3) {
        this.equipe3 = equipe3;
    }


}