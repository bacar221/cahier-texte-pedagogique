package com.cahiertexte.model;

import java.time.LocalTime;
import java.io.Serializable;

/**
 * Modèle représentant un cours
 */
public class Cours implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private int matiereId;
    private int enseignantId;
    private int classeId;
    private String jourSemaine;
    private LocalTime heureDebut;
    private int dureeMinutes;

    // Constructeurs
    public Cours() {}

    public Cours(int matiereId, int enseignantId, int classeId) {
        this.matiereId = matiereId;
        this.enseignantId = enseignantId;
        this.classeId = classeId;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMatiereId() { return matiereId; }
    public void setMatiereId(int matiereId) { this.matiereId = matiereId; }

    public int getEnseignantId() { return enseignantId; }
    public void setEnseignantId(int enseignantId) { this.enseignantId = enseignantId; }

    public int getClasseId() { return classeId; }
    public void setClasseId(int classeId) { this.classeId = classeId; }

    public String getJourSemaine() { return jourSemaine; }
    public void setJourSemaine(String jourSemaine) { this.jourSemaine = jourSemaine; }

    public LocalTime getHeureDebut() { return heureDebut; }
    public void setHeureDebut(LocalTime heureDebut) { this.heureDebut = heureDebut; }

    public int getDureeMinutes() { return dureeMinutes; }
    public void setDureeMinutes(int dureeMinutes) { this.dureeMinutes = dureeMinutes; }

    @Override
    public String toString() {
        return "Cours{" +
                "id=" + id +
                ", matiereId=" + matiereId +
                ", enseignantId=" + enseignantId +
                ", classeId=" + classeId +
                '}';
    }
}