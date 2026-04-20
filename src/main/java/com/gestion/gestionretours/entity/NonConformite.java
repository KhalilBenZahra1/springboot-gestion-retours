package com.gestion.gestionretours.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "non_conformite")
public class NonConformite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String gravite;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String produit;

    public NonConformite() {
    }

    public NonConformite(Long id, String description, String gravite, LocalDate date, String produit) {
        this.id = id;
        this.description = description;
        this.gravite = gravite;
        this.date = date;
        this.produit = produit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGravite() {
        return gravite;
    }

    public void setGravite(String gravite) {
        this.gravite = gravite;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }
}