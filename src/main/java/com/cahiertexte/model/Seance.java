package com.cahiertexte.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.io.Serializable;

/**
 * Modèle représentant une séance pédagogique
 */
public class Seance implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private int coursId;
    private LocalDate dateSeance;
    private LocalTime heureDebut;
    private int dureeMinutes;
    private String contenu;
    private String observations;
    private StatutValidation statutValidation;
    private LocalDateTime dateCreation;
    private LocalDateTime dateValidation;

    public enum StatutValidation {
        EN_ATTENTE("En attente"),
        VALIDEE("Validée"),
        REJETEE("Rejetée");

        private final String label;
        StatutValidation(String label) {
            this.label = label;
        }
        public String getLabel() {
            return label;
        }
    }

    // Constructeurs
    public Seance() {
        this.dateCreation = LocalDateTime.now();
        this.statutValidation = StatutValidation.EN_ATTENTE;
    }

    public Seance(int coursId, LocalDate dateSeance, LocalTime heureDebut, 
                  int dureeMinutes, String contenu) {
        this();
        this.coursId = coursId;
        this.dateSeance = dateSeance;
        this.heureDebut = heureDebut;
        this.dureeMinutes = dureeMinutes;
        this.contenu = contenu;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCoursId() { return coursId; }
    public void setCoursId(int coursId) { this.coursId = coursId; }

    public LocalDate getDateSeance() { return dateSeance; }
    public void setDateSeance(LocalDate dateSeance) { this.dateSeance = dateSeance; }

    public LocalTime getHeureDebut() { return heureDebut; }
    public void setHeureDebut(LocalTime heureDebut) { this.heureDebut = heureDebut; }

    public int getDureeMinutes() { return dureeMinutes; }
    public void setDureeMinutes(int dureeMinutes) { this.dureeMinutes = dureeMinutes; }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }

    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }

    public StatutValidation getStatutValidation() { return statutValidation; }
    public void setStatutValidation(StatutValidation statutValidation) { this.statutValidation = statutValidation; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public LocalDateTime getDateValidation() { return dateValidation; }
    public void setDateValidation(LocalDateTime dateValidation) { this.dateValidation = dateValidation; }

    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", coursId=" + coursId +
                ", dateSeance=" + dateSeance +
                ", heureDebut=" + heureDebut +
                ", dureeMinutes=" + dureeMinutes +
                ", statutValidation=" + statutValidation +
                '}';
    }
}