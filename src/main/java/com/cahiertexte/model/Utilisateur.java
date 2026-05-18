package com.cahiertexte.model;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * Modèle représentant un utilisateur du système
 */
public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private RoleUtilisateur role;
    private StatutUtilisateur statut;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;

    // Énumération des rôles
    public enum RoleUtilisateur {
        CHEF_DEPARTEMENT("Chef de Département"),
        ENSEIGNANT("Enseignant"),
        RESPONSABLE_CLASSE("Responsable de Classe");

        private final String label;
        RoleUtilisateur(String label) {
            this.label = label;
        }
        public String getLabel() {
            return label;
        }
    }

    // Énumération des statuts
    public enum StatutUtilisateur {
        ACTIF("Actif"),
        INACTIF("Inactif"),
        EN_ATTENTE("En attente");

        private final String label;
        StatutUtilisateur(String label) {
            this.label = label;
        }
        public String getLabel() {
            return label;
        }
    }

    // Constructeurs
    public Utilisateur() {
        this.dateCreation = LocalDateTime.now();
        this.dateModification = LocalDateTime.now();
        this.statut = StatutUtilisateur.EN_ATTENTE;
    }

    public Utilisateur(String nom, String prenom, String email, String motDePasse, RoleUtilisateur role) {
        this();
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getNomComplet() {
        return prenom + " " + nom;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public RoleUtilisateur getRole() { return role; }
    public void setRole(RoleUtilisateur role) { this.role = role; }

    public StatutUtilisateur getStatut() { return statut; }
    public void setStatut(StatutUtilisateur statut) { this.statut = statut; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public LocalDateTime getDateModification() { return dateModification; }
    public void setDateModification(LocalDateTime dateModification) { this.dateModification = dateModification; }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", statut=" + statut +
                '}';
    }
}