# Projet Spring Boot avec Thymeleaf - README

Ce document décrit les fonctionnalités et les relations du projet Spring Boot avec Thymeleaf pour la gestion des joueurs, des équipes, des défis et des résultats.

---

## Description du projet
Le projet permet de gérer des entités telles que des joueurs, des équipes, des défis et des résultats à travers une application web dynamique. Les principales fonctionnalités incluent l'ajout et la gestion des défis par des sociétés, et la possibilité pour un administrateur d'accepter ou de refuser les défis proposés.

---

### Entités principales et leurs relations

#### 1. **Joueur**
- **Description**: Représente un joueur appartenant à une équipe.
- **Relation**: 
  - `@OneToOne` avec l'entité **Équipe**.

#### 2. **Équipe**
- **Description**: Représente une équipe composée de joueurs.
- **Relation**:
  - `@OneToOne` avec l'entité **Joueur**.
  - `@ManyToOne` avec l'entité **Défi**.

#### 3. **Défi**
- **Description**: Représente un défi proposé par une société et qui peut être accepté ou refusé par l'administrateur.
- **Relation**:
  - `@ManyToOne` avec l'entité **Équipe**.
  - `@ManyToMany` avec l'entité **Résultat**.

#### 4. **Résultat**
- **Description**: Représente les résultats associés aux défis.
- **Relation**:
  - `@ManyToMany` avec l'entité **Défi**.

---



## Contributeurs
- Développeur : **Mohamed Ouni**

---



