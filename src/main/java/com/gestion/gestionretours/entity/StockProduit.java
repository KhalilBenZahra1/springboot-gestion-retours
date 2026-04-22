package com.gestion.gestionretours.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "stock_produit")
public class StockProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le produit est obligatoire.")
    @Column(nullable = false, unique = true)
    private String produit;

    @NotNull(message = "La quantité est obligatoire.")
    @Min(value = 0, message = "La quantité ne peut pas être négative.")
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