package com.gestion.gestionretours.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "historique_retour")
public class HistoriqueRetour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String retour;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private String employe;

    @Column(nullable = false)
    private LocalDate date;

    public HistoriqueRetour() {
    }

    public HistoriqueRetour(Long id, String retour, String action, String employe, LocalDate date) {
        this.id = id;
        this.retour = retour;
        this.action = action;
        this.employe = employe;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getRetour() {
        return retour;
    }

    public String getAction() {
        return action;
    }

    public String getEmploye() {
        return employe;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRetour(String retour) {
        this.retour = retour;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setEmploye(String employe) {
        this.employe = employe;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}