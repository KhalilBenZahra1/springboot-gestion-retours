package com.gestion.gestionretours.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "non_conformite")
public class NonConformite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La description est obligatoire.")
    @Column(nullable = false)
    private String description;

    @NotBlank(message = "La gravité est obligatoire.")
    @Column(nullable = false)
    private String gravite;

    @Column(nullable = false)
    private LocalDate date;

    @NotBlank(message = "Le produit est obligatoire.")
    @Column(nullable = false)
    private String produit;

    @Column(nullable = false)
    private String statut;

    public NonConformite() {
    }

    public NonConformite(Long id, String description, String gravite, LocalDate date, String produit, String statut) {
        this.id = id;
        this.description = description;
        this.gravite = gravite;
        this.date = date;
        this.produit = produit;
        this.statut = statut;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getGravite() {
        return gravite;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getProduit() {
        return produit;
    }

    public String getStatut() {
        return statut;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGravite(String gravite) {
        this.gravite = gravite;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}