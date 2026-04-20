package com.gestion.gestionretours.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "retour_produit")
public class RetourProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String produit;

    @Column(nullable = false)
    private String client;

    @Column(nullable = false)
    private String raison;

    @Column(name = "etat_traitement", nullable = false)
    private String etatTraitement;

    @Column(nullable = false)
    private LocalDate date;

    public RetourProduit() {
    }

    public RetourProduit(Long id, String produit, String client, String raison, String etatTraitement, LocalDate date) {
        this.id = id;
        this.produit = produit;
        this.client = client;
        this.raison = raison;
        this.etatTraitement = etatTraitement;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public String getEtatTraitement() {
        return etatTraitement;
    }

    public void setEtatTraitement(String etatTraitement) {
        this.etatTraitement = etatTraitement;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}