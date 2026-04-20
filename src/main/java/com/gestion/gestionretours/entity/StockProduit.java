package com.gestion.gestionretours.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stock_produit")
public class StockProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String produit;

    @Column(nullable = false)
    private Integer quantite;

    public StockProduit() {
    }

    public StockProduit(Long id, String produit, Integer quantite) {
        this.id = id;
        this.produit = produit;
        this.quantite = quantite;
    }

    public Long getId() {
        return id;
    }

    public String getProduit() {
        return produit;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
}